package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers;

public abstract class NormalizeFields {

    protected String normalizeField(String text) {
        return text.replaceAll("\\D", "");
    }

}
