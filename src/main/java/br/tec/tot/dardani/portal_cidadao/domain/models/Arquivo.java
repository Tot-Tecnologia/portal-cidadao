package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.Arrays;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Arquivo {

    private static final String PDF_MIME_TYPE = "application/pdf";
    private static final String NOME_ORIGINAL_NULO = "Nome original não pode ser nulo";
    private static final String TAMANHO_NULO = "Tamanho não pode ser nulo";
    private static final String PDF_NAO_PERMITIDO = "Somente arquivos PDF são permitidos";
    private static final String CONTEUDO_VAZIO = "Conteúdo do arquivo não pode ser vazio";

    private final Long id;
    private final String nomeOriginal;
    private final String mimeType;
    private final byte[] conteudo;
    private final Long tamanho;
    private final TipoArquivoEnum tipoArquivo;

    private Arquivo(Long id, String nomeOriginal, String mimeType, byte[] conteudo, Long tamanho,
            TipoArquivoEnum tipoArquivo) {
        this.id = id;
        this.nomeOriginal = validarNomeOriginal(nomeOriginal);
        this.mimeType = validarMimeType(mimeType);
        this.conteudo = validarConteudo(conteudo);
        this.tamanho = validarTamanho(tamanho);
        this.tipoArquivo = validarTipoArquivo(tipoArquivo);
    }

    public static Arquivo criar(Long id, String nomeOriginal, String mimeType, byte[] conteudo, Long tamanho,
            TipoArquivoEnum tipoArquivo) {
        return new Arquivo(id, nomeOriginal, mimeType, conteudo, tamanho, tipoArquivo);
    }

    private TipoArquivoEnum validarTipoArquivo(TipoArquivoEnum tipoArquivo) {
        if (tipoArquivo == null) {
            throw new DomainException("Tipo do arquivo é obrigátorio");
        }

        return tipoArquivo;
    }

    private String validarNomeOriginal(String nomeOriginal) {
        if (nomeOriginal == null) {
            throw new DomainException(NOME_ORIGINAL_NULO);
        }
        return nomeOriginal;
    }

    private String validarMimeType(String mimeType) {
        if (mimeType == null) {
            throw new DomainException(PDF_NAO_PERMITIDO);
        }
        if (!PDF_MIME_TYPE.equalsIgnoreCase(mimeType)) {
            throw new DomainException(PDF_NAO_PERMITIDO);
        }
        return mimeType;
    }

    private byte[] validarConteudo(byte[] conteudo) {
        if (conteudo == null || conteudo.length == 0) {
            throw new DomainException(CONTEUDO_VAZIO);
        }
        return Arrays.copyOf(conteudo, conteudo.length);
    }

    private Long validarTamanho(Long tamanho) {
        if (tamanho == null) {
            throw new DomainException(TAMANHO_NULO);
        }
        return tamanho;
    }

    public byte[] getConteudo() {
        return Arrays.copyOf(conteudo, conteudo.length);
    }

}