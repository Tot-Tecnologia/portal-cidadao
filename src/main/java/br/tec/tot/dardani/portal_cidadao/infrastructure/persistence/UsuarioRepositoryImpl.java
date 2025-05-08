package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.UsuarioRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers.UsuarioMapper;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.UsuarioJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public Optional<Usuario> buscarPessoa(String email, String cpfCnpj) {
        return repository.findByContatoEmailOrDocumento(email, cpfCnpj).map(mapper::toModel);
    }

}
