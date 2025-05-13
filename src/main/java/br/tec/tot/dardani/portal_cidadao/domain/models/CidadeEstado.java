package br.tec.tot.dardani.portal_cidadao.domain.models;

import java.util.List;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class CidadeEstado {
    private final String cidade;
    private final String estado;

    public CidadeEstado(String cidade, String estado) {
        this.cidade = validarCidade(cidade);
        this.estado = validarEstado(estado);
    }

    private String validarCidade(String cidade) {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new DomainException("Cidade não pode ser nula ou vazia");
        }

        String cidadeTrimmed = cidade.trim();

        if (cidadeTrimmed.length() < 3) {
            throw new DomainException("Cidade deve ter pelo menos 3 caracteres");
        }

        if (!cidadeTrimmed.matches("^[a-zA-ZÀ-ú\\s]+$")) {
            throw new DomainException("Cidade deve conter apenas letras e espaços");
        }

        return cidadeTrimmed;
    }

    private String validarEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new DomainException("Estado não pode ser nulo ou vazio");
        }

        String estadoTrimmed = estado.trim().toUpperCase();

        if (estadoTrimmed.length() != 2) {
            throw new DomainException("Estado deve ser a sigla com 2 caracteres");
        }

        if (!estadoTrimmed.matches("^[A-Z]{2}$")) {
            throw new DomainException("Estado deve conter apenas letras maiúsculas");
        }

        String[] estadosBR = { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
                "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
                "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" };

        boolean valido = List.of(estadosBR).stream().anyMatch(e -> e.equals(estadoTrimmed));

        if (!valido) {
            throw new DomainException("Sigla de estado inválida");
        }

        return estadoTrimmed;
    }

}
