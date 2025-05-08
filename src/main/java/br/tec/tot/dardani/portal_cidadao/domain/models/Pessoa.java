package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import lombok.Getter;

@Getter
public abstract class Pessoa {

    private final Long id;
    private final Contato contato;
    private final CpfCnpj documento;
    private final String nome;

    public Pessoa(Long id, String nome, CpfCnpj documento, Contato contato) {
        this.id = id;
        this.nome = validarNome(nome);
        this.contato = validarContato(contato);
        this.documento = validarCpfCnpj(documento);
    }

    public Pessoa(String nome, CpfCnpj documento, Contato contato) {
        this(null, nome, documento, contato);
    }

    private String validarNome(String nomeSolicitante) {
        if (nomeSolicitante == null || nomeSolicitante.trim().isEmpty()) {
            throw new DomainException("Nome não pode ser nulo ou vazio");
        }

        String nomeTrimmed = nomeSolicitante.trim();

        if (nomeTrimmed.length() < 3) {
            throw new DomainException("Nome deve ter pelo menos 3 caracteres");
        }

        if (!nomeTrimmed.matches("^[\\p{L} ]+$")) {
            throw new DomainException("Nome deve conter apenas letras e espaços");
        }

        return nomeTrimmed;
    }

    private CpfCnpj validarCpfCnpj(CpfCnpj cpfCnpj) {
        if (cpfCnpj == null) {
            throw new DomainException("CPF/CNPJ é obrigatório");
        }

        return cpfCnpj;
    }

    private Contato validarContato(Contato contato) {
        if (contato == null) {
            throw new DomainException("CPF/CNPJ é obrigatório");
        }

        return contato;
    }

}
