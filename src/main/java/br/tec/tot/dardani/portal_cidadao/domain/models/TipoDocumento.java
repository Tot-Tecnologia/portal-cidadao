package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;

@Getter
public final class TipoDocumento {

    private final Long id;
    private final String nome;

    public TipoDocumento(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public static Collection<TipoDocumento> buscarDocumentos() {
        return Arrays.asList(
                new TipoDocumento(1L, "Certidão Negativa de Débitos (CND)"),
                new TipoDocumento(2L, "Certidão Positiva com Efeitos de Negativa (CPEN)"),
                new TipoDocumento(3L, "Guia de Recolhimento Tributário (GRT)"),
                new TipoDocumento(4L, "Documento de Arrecadação de Receitas Federais (DARF)"),
                new TipoDocumento(5L, "Alvará de Funcionamento"),
                new TipoDocumento(6L, "Alvará de Construção/Licença de Obras"),
                new TipoDocumento(7L, "Licença Ambiental"),
                new TipoDocumento(8L, "Alvará de Publicidade"),
                new TipoDocumento(9L, "Habite-se"),
                new TipoDocumento(10L, "Certidão de Zoneamento"),
                new TipoDocumento(11L, "Matrícula Atualizada do Imóvel"),
                new TipoDocumento(12L, "Auto de Vistoria do Corpo de Bombeiros (AVCB)"),
                new TipoDocumento(13L, "Certidão de Regularidade Fiscal (CRF)"),
                new TipoDocumento(14L, "Comprovante de Quitação de Taxas (Taxa de Lixo)"),
                new TipoDocumento(15L, "Registro de Empresa no Cadastro Municipal (CCM)"),
                new TipoDocumento(16L, "Comprovante de Inscrição no IPTU"),
                new TipoDocumento(17L, "Alvará Sanitário"),
                new TipoDocumento(18L, "Licença para Eventos"),
                new TipoDocumento(19L, "Certidão de Uso e Ocupação do Solo"),
                new TipoDocumento(20L, "Requisição de Serviços Públicos"));
    }

}
