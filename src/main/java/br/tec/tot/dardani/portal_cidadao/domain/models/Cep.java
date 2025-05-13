package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class Cep {

    private final String valor;

    public Cep(String cep) {
        this.valor = validarCep(cep);
    }

    private String validarCep(String cep) {
        if (cep == null || cep.trim().isEmpty()) {
            throw new DomainException("CEP não pode ser nulo ou vazio");
        }

        String cepNumerico = cep.replaceAll("[^0-9]", "");

        if (cep.contains("-") && !cep.matches("^\\d{5}-\\d{3}$")) {
            throw new DomainException("Formato de CEP inválido. Use o padrão 12345-678");
        }

        if (cepNumerico.length() != 8) {
            throw new DomainException("CEP deve conter 8 dígitos");
        }

        return cepNumerico;
    }
}
