package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.ArquivoEntity;

@Repository
public interface ArquivoJpaRepository extends JpaRepository<ArquivoEntity, Long> {

}
