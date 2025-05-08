package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public final class Endereco {

    private final Cep cep;
    private final CidadeEstado cidadeEstado;
    private final Localizacao localizacao;

    public Endereco(Localizacao localizacao, CidadeEstado cidadeEstado, Cep cep) {
        this.localizacao = Objects.requireNonNull(localizacao, "Localização é obrigatória");
        this.cidadeEstado = Objects.requireNonNull(cidadeEstado, "Cidade/Estado é obrigatório");
        this.cep = Objects.requireNonNull(cep, "CEP é obrigatório");
    }

}