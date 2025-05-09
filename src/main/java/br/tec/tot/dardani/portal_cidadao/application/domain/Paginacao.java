package br.tec.tot.dardani.portal_cidadao.application.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class Paginacao {

    private Integer paginaAtual;
    private Integer itensPagina;

    private String[] sort = new String[] { "id", "asc" };

}
