package br.tec.tot.dardani.portal_cidadao.application.controllers.advices.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseError {
    private Integer statusCode;
    private LocalDateTime dateTime;
    private List<MessageError> errors;

    public static ResponseError response(List<MessageError> errors, HttpStatus statusCode) {
        return new ResponseError(statusCode.value(), LocalDateTime.now(), errors);
    }
}
