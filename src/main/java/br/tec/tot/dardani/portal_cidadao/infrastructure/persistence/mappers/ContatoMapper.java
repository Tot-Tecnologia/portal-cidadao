package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.Contato;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.ContatoEmbeddable;

@Component
public class ContatoMapper {

    public ContatoEmbeddable toContatoEmbeddable(Contato modelo) {
        return new ContatoEmbeddable(
                modelo.getTelefone(),
                modelo.getEmail());
    }

    public Contato toModel(ContatoEmbeddable embeddable) {
        return new Contato(
                embeddable.getTelefone(),
                embeddable.getEmail());
    }
}
