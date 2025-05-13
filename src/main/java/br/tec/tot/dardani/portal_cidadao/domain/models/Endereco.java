package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class Endereco {

    private final Cep cep;
    private final CidadeEstado cidadeEstado;
    private final Localizacao localizacao;

    public Endereco(Localizacao localizacao, CidadeEstado cidadeEstado, Cep cep) {
        this.localizacao = validarLocalizacao(localizacao);
        this.cidadeEstado = validarCidadeEstado(cidadeEstado);
        this.cep = validarCep(cep);
    }

    private Localizacao validarLocalizacao(Localizacao localizacao) {
        if (localizacao == null) {
            throw new DomainException("Localização é obrigatória");
        }
        return localizacao;
    }

    private CidadeEstado validarCidadeEstado(CidadeEstado cidadeEstado) {
        if (cidadeEstado == null) {
            throw new DomainException("Cidade/Estado é obrigatório");
        }
        return cidadeEstado;
    }

    private Cep validarCep(Cep cep) {
        if (cep == null) {
            throw new DomainException("CEP é obrigatório");
        }
        return cep;
    }

}