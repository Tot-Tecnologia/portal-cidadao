package br.tec.tot.dardani.portal_cidadao.application.controllers.advices.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageError {
    private String message;
    private String field;
}
