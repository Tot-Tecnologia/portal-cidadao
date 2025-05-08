package br.tec.tot.dardani.portal_cidadao.application.domain;

import lombok.Getter;

@Getter
public abstract class Paginacao {

    private Integer paginaAtual = 1;
    private Integer itensPagina = 10;

    private String[] sort = new String[] { "id", "asc" };

}
