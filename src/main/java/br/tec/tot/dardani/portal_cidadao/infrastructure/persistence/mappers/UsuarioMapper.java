package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.domain.models.CpfCnpj;
import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.UsuarioEntity;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final ContatoMapper contatoMapper;

    public Usuario toModel(UsuarioEntity entidade) {
        return new Usuario(entidade.getId(), entidade.getNome(), entidade.getNome(), entidade.getLogin(),
                new CpfCnpj(entidade.getDocumento()), contatoMapper.toModel(entidade.getContato()));
    }

}
