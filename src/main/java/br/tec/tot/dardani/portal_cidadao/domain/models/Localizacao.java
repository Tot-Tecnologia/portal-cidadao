package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public class Localizacao {
    private final String logradouro;
    private final String numero;
    private final String bairro;
    private final String complemento;

    public Localizacao(String logradouro, String numero, String bairro, String complemento) {
        this.logradouro = validarLogradouro(logradouro);
        this.numero = validarNumero(numero);
        this.bairro = validarBairro(bairro);
        this.complemento = complemento != null ? complemento.trim() : null;
    }

    private String validarLogradouro(String logradouro) {
        if (logradouro == null || logradouro.trim().isEmpty()) {
            throw new DomainException("Logradouro não pode ser nulo ou vazio");
        }

        String logradouroTrimmed = logradouro.trim();

        if (logradouroTrimmed.length() < 3) {
            throw new DomainException("Logradouro deve ter pelo menos 3 caracteres");
        }

        return logradouroTrimmed;
    }

    private String validarNumero(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            throw new DomainException("Número não pode ser nulo ou vazio");
        }

        String numeroTrimmed = numero.trim();

        if (!numeroTrimmed.matches("^[0-9]+[A-Za-z]?$")) {
            throw new DomainException("Número deve conter apenas dígitos e opcionalmente uma letra");
        }

        return numeroTrimmed;
    }

    private String validarBairro(String bairro) {
        if (bairro == null || bairro.trim().isEmpty()) {
            throw new DomainException("Bairro não pode ser nulo ou vazio");
        }

        String bairroTrimmed = bairro.trim();

        if (bairroTrimmed.length() < 3) {
            throw new DomainException("Bairro deve ter pelo menos 3 caracteres");
        }

        return bairroTrimmed;
    }

}
