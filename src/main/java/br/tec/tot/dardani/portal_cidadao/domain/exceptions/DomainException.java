package br.tec.tot.dardani.portal_cidadao.domain.exceptions;

import org.springframework.http.HttpStatus;

public class DomainException extends ApiException {

    public DomainException(String message) {
        super(message);
    }

    public DomainException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

}
