package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.specifications.ProtocoloSpecifications;

@Repository
public interface ProtocoloJpaRepository
        extends JpaRepository<ProtocoloEntity, Long>, JpaSpecificationExecutor<ProtocoloEntity> {

    default Page<ProtocoloEntity> consultarProcolos(ProtocoloFiltrosRequest filtros, Pageable pageable) {
        return findAll(ProtocoloSpecifications.consultarProtocolos(filtros), pageable);
    }
}
