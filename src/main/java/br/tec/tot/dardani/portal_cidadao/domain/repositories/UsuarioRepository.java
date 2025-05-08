package br.tec.tot.dardani.portal_cidadao.domain.repositories;

import java.util.Optional;

import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;

public interface UsuarioRepository {

    Optional<Usuario> buscarPessoa(String email, String cpfCnpj);

}
