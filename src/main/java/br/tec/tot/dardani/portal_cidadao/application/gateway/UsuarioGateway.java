package br.tec.tot.dardani.portal_cidadao.application.gateway;

import java.util.Optional;

import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;

public interface UsuarioGateway {

    Optional<Usuario> buscarPessoa(String email, String cpfCnpj);

}
