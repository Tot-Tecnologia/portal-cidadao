package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public final class Contato {

    private final String telefone;
    private final String email;

    public Contato(final String telefone, final String email) {
        this.telefone = validarTelefone(telefone);
        this.email = validarEmail(email);
    }

    private String validarTelefone(String telefone) {
        if (telefone == null) {
            throw new DomainException("Telefone não pode ser nulo");
        }

        String telefoneTrimmed = telefone.trim();

        if (telefoneTrimmed.isEmpty()) {
            throw new DomainException("Telefone não pode ser vazio");
        }

        String numeroLimpo = telefoneTrimmed.replaceAll("[^0-9]", "");

        if (numeroLimpo.length() < 10) {
            throw new DomainException("Telefone deve ter no mínimo 10 dígitos");
        }

        if (numeroLimpo.length() > 11) {
            throw new DomainException("Telefone deve ter no máximo 11 dígitos");
        }

        if (!numeroLimpo.matches("\\d+")) {
            throw new DomainException("Telefone deve conter apenas números");
        }

        return numeroLimpo;
    }

    private String validarEmail(String email) {
        if (email == null) {
            throw new DomainException("Email não pode ser nulo");
        }

        String emailTrimmed = email.trim();

        if (email.isBlank()) {
            throw new DomainException("Email não pode ser vazio");
        }

        if (!emailTrimmed.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new DomainException("Formato de email inválido");
        }

        return emailTrimmed;
    }

}