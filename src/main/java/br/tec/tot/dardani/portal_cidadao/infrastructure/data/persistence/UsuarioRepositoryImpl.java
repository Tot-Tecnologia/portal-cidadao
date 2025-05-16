package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.domain.repositories.UsuarioRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.UsuarioEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.repositories.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final UsuarioJpaRepository repository;

    @Override
    public Optional<UsuarioEntity> buscarUsuario(String email, String cpfCnpj) {
        return repository.findByContatoEmailOrDocumento(email, cpfCnpj);
    }

}
