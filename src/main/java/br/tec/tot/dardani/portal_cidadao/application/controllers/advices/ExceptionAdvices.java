package br.tec.tot.dardani.portal_cidadao.application.controllers.advices;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.tec.tot.dardani.portal_cidadao.application.controllers.advices.domain.MessageError;
import br.tec.tot.dardani.portal_cidadao.application.controllers.advices.domain.ResponseError;
import br.tec.tot.dardani.portal_cidadao.application.controllers.advices.domain.ResponseMessage;
import br.tec.tot.dardani.portal_cidadao.application.security.exception.InvalidTokenExeception;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ProtocoloNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvices extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler({ InvalidTokenExeception.class })
    public ResponseEntity<Object> handleInvalidTokenExeception(InvalidTokenExeception exception, WebRequest request) {

        return handleExceptionInternal(exception, new ResponseMessage(exception.getMessage()), new HttpHeaders(),
                exception.getHttpStatus(), request);

    }

    @ExceptionHandler({ ProtocoloNotFoundException.class })
    public ResponseEntity<Object> handleProtocoloNotFoundException(ProtocoloNotFoundException exception,
            WebRequest request) {
        return handleExceptionInternal(exception, new ResponseMessage(exception.getMessage()), new HttpHeaders(),
                exception.getHttpStatus(), request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<MessageError> erros = listDeErro(
                ex.getBindingResult());

        return handleExceptionInternal(ex,
                ResponseError.response(erros, HttpStatus.BAD_REQUEST), headers,
                HttpStatus.BAD_REQUEST, request);
    }

    private List<MessageError> listDeErro(BindingResult bindingResult) {
        List<MessageError> erros = new ArrayList<>();

        for (FieldError fError : bindingResult.getFieldErrors()) {

            String mensagem = messageSource.getMessage(fError, LocaleContextHolder.getLocale());
            String campo = fError.getField();

            erros.add(new MessageError(mensagem, campo));
        }
        return erros;
    }

}
