package br.tec.tot.dardani.portal_cidadao.infrastructure.security.model;

import lombok.Getter;

@Getter
public final class Sessao {
    public static final String KEY = "SESSAO";
    private final String uid;
    private final String login;

    public Sessao(final String uid, final String login) {
        this.uid = String.valueOf(uid);
        this.login = login;
    }

}
