package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public final class Guia extends Arquivo {

    private final StatusGuiaEnum status;

    private LocalDateTime dataPagamento;
    private LocalDateTime dataCriacao;

    public Guia(Long id,
            String nomeOriginal,
            String mimeType,
            byte[] conteudo,
            Long tamanho) {
        super(id, nomeOriginal, mimeType, conteudo, tamanho);
        this.status = StatusGuiaEnum.PENDENTE;
    }

}
