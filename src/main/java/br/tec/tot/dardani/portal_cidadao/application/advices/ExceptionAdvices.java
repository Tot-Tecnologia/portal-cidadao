package br.tec.tot.dardani.portal_cidadao.application.advices;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.RespostaPadrao;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvices {

    @ExceptionHandler(exception = ApiException.class, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RespostaPadrao> apiException(ApiException exception) {

        var response = new RespostaPadrao(
                exception.getHttpStatus().value(),
                exception.getMessage(),
                exception.getHttpStatus().name(), LocalDateTime.now());

        return ResponseEntity.status(exception.getHttpStatus().value()).body(response);

    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<RespostaPadrao> unknowException(Exception ex) {
        var response = new RespostaPadrao(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Ocorreu um erro interno. Tente mais tarde.", HttpStatus.INTERNAL_SERVER_ERROR.name(),
                LocalDateTime.now());

        log.error("Falha na requisicao", ex);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(response);
    }

    @ExceptionHandler(exception = MaxUploadSizeExceededException.class)
    public ResponseEntity<RespostaPadrao> maxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
        var response = new RespostaPadrao(400, "Os arquivos selecionados excederam o tamanho máximo permitido.",
                HttpStatus.BAD_REQUEST.name(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(response);

    }
}
