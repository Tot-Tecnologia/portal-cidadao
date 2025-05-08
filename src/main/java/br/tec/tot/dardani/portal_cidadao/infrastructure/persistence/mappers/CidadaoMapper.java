package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.CidadaoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CidadaoMapper {

    private final ContatoMapper contatoMapper;

    public CidadaoEntity toEntity(Cidadao modelo) {

        var entidade = new CidadaoEntity();
        entidade.setContato(contatoMapper.toContatoEmbeddable(modelo.getContato()));
        entidade.setDocumento(modelo.getDocumento().getValor());
        entidade.setLogin(modelo.getLogin());
        entidade.setSenha(modelo.getSenha());
        entidade.setNome(modelo.getNome());
        return entidade;

    }

    public Cidadao toModel(CidadaoEntity entidade) {
        return new Cidadao(entidade.getId(), entidade.getNome(), entidade.getSenha(), entidade.getLogin(),
                new CpfCnpj(entidade.getDocumento()), contatoMapper.toModel(entidade.getContato()));
    }

}
