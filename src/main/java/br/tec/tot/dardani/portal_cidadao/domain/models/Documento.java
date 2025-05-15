package br.tec.tot.dardani.portal_cidadao.domain.models;

public final class Documento extends Arquivo {

    public Documento(Long id, String nomeOriginal, String mimeType, byte[] conteudo, Long tamanho) {
        super(id, nomeOriginal, mimeType, conteudo, tamanho);
    }

}
