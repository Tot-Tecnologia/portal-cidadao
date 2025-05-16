package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.CidadaoRequest;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.CidadaoEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.embeddables.ContatoEmbeddable;

@Component
public class CidadaoMapper {

    public CidadaoEntity toEntity(CidadaoRequest request) {

        var entity = new CidadaoEntity();
        var contato = new ContatoEmbeddable(
                request.telefone(),
                request.email());

        entity.setContato(contato);

        entity.setDocumento(request.cpfCnpj());
        entity.setLogin(request.email());
        entity.setNome(request.nome());
        entity.setSenha(request.senha());

        return entity;
    }

}
