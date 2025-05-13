package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.PessoaEntity;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long> {

}
