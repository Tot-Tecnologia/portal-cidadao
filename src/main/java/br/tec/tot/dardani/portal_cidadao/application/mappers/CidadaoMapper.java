package br.tec.tot.dardani.portal_cidadao.application.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.CidadaoRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.models.Contato;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.CidadaoEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.embeddables.ContatoEmbeddable;

@Component
public class CidadaoMapper {

    public Cidadao toDomain(CidadaoRequest request) {
        return new Cidadao(
                request.nome(),
                request.senha(),
                request.email(),
                new CpfCnpj(request.cpfCnpj()),
                new Contato(request.telefone(),
                        request.email()));
    }

    public CidadaoEntity toEntity(Cidadao model) {
        var entity = new CidadaoEntity();
        var contato = new ContatoEmbeddable(
                model.getContato().getTelefone(),
                model.getContato().getEmail());

        entity.setContato(contato);

        entity.setDocumento(model.getCpfCnpj());
        entity.setLogin(model.getLogin());
        entity.setNome(model.getNome());
        entity.setSenha(model.getSenha());

        return entity;
    }

}
