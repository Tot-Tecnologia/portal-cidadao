package br.tec.tot.dardani.portal_cidadao.infrastructure.security.model;

import lombok.Getter;

@Getter
public final class Sessao {
    public static final String KEY = "SESSAO";
    private final Long uid;
    private final String login;

    public Sessao(final Long uid, final String login) {
        this.uid = uid;
        this.login = login;
    }

}
