package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.PessoaEntity;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, Long> {

}
