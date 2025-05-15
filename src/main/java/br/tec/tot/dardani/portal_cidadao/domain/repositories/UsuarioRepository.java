package br.tec.tot.dardani.portal_cidadao.domain.repositories;

import java.util.Optional;

import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.UsuarioEntity;

public interface UsuarioRepository {

    Optional<UsuarioEntity> buscarUsuario(String email, String cpfCnpj);

}
