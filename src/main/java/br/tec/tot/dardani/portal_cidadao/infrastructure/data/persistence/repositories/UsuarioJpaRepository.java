package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.UsuarioEntity;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByContatoEmailOrDocumento(String email, String documento);

}
