package br.tec.tot.dardani.portal_cidadao.application.security.exception;

import org.springframework.http.HttpStatus;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;

public class InvalidTokenExeception extends ApiException {

    public InvalidTokenExeception(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }

}
