package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class PaginacaoRequest {

    private Integer paginaAtual = 0;
    private Integer itensPagina = 10;

    private String[] sort = new String[] { "id", "asc" };

}
