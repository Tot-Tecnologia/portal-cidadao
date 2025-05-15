package br.tec.tot.dardani.portal_cidadao.domain.models;

public final class Cidadao extends Usuario {

    public Cidadao(Long id, String nome, String senha, String login, CpfCnpj documento, Contato contato) {
        super(id, nome, senha, login, documento, contato);
    }

    public Cidadao(String nome, String senha, String login, CpfCnpj documento, Contato contato) {
        super(null, nome, senha, login, documento, contato);
    }

    public String getEmail() {
        return this.getContato().getEmail();
    }

    public String getCpfCnpj() {
        return this.getDocumento().getValor();
    }

}
