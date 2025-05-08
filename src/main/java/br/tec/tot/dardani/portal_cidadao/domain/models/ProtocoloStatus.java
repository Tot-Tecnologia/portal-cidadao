package br.tec.tot.dardani.portal_cidadao.domain.models;

import lombok.Getter;

@Getter
public enum ProtocoloStatus {
    ABERTO("Aberto"),
    EM_ANALISE("Em Análise"),
    APROVADO("Aprovado"),
    REJEITADO("Rejeitado");

    private final String descricao;

    private ProtocoloStatus(String descricao) {
        this.descricao = descricao;
    }
}
