package br.tec.tot.dardani.portal_cidadao.application.domain;

public abstract class Paginacao {

    private Integer paginaAtual;
    private Integer itensPagina;
    private String[] sort;

    public int getPaginaAtual() {
        return paginaAtual != null ? paginaAtual : 1;
    }

    public int getItensPagina() {
        return itensPagina != null ? itensPagina : 10;
    }

    public String[] getSort() {
        return sort != null ? sort : new String[]{"id", "asc"};
    }
}
