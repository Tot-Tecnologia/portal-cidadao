package br.tec.tot.dardani.portal_cidadao.infrastructure.gateway;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.gateway.UsuarioGateway;
import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioGatewayImpl implements UsuarioGateway {

    private final UsuarioRepository repository;

    @Override
    public Optional<Usuario> buscarPessoa(String email, String cpfCnpj) {
        return this.repository.buscarPessoa(email, cpfCnpj);
    }

}
