package br.tec.tot.dardani.portal_cidadao.domain.models;

import lombok.Getter;

@Getter
public class Usuario extends Pessoa {

    private final String senha;
    private final String login;

    public Usuario(Long id, String nome, String senha, String login, CpfCnpj documento, Contato contato) {
        super(id, nome, documento, contato);
        this.senha = senha;
        this.login = login;
    }

    public Usuario(String nome, String senha, String login, CpfCnpj documento, Contato contato) {
        super(null, nome, documento, contato);
        this.senha = senha;
        this.login = login;
    }

}
