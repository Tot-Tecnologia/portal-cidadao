package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public class Cep {

    private final String valor;

    public Cep(String cep) {
        this.valor = validarCep(cep);
    }

    private String validarCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new DomainException("CEP não pode ser nulo ou vazio");
        }

        String cepTrimmed = cep.trim();

        if (cepTrimmed.length() != 8) {
            throw new DomainException("CEP deve conter 8 dígitos");
        }

        if (!cepTrimmed.matches("^[0-9]{8}$")) {
            throw new DomainException("CEP deve conter apenas números");
        }

        return cepTrimmed;
    }
}
