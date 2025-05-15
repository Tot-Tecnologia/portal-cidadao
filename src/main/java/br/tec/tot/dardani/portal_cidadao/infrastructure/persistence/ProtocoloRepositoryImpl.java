
package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.ProtocoloJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProtocoloRepositoryImpl extends AbstractRepository implements ProtocoloRepository {

    private final ProtocoloJpaRepository jpaRepository;

    @Override
    public ProtocoloEntity salvar(ProtocoloEntity entidade) {
        return jpaRepository.save(entidade);
    }

    @Override
    public Page<ProtocoloEntity> buscarProtocolos(ProtocoloFiltrosRequest parametros) {
        return jpaRepository.consultarProtocolos(parametros, parsePaginacao(parametros));
    }

    @Override
    public Optional<ProtocoloEntity> buscarProtocoloPorNumeroProtocolo(String numeroProtocolo) {
        return jpaRepository.findByNumeroProtocolo(numeroProtocolo);
    }

    @Override
    public Optional<ProtocoloEntity> buscarProtocoloPorId(Long protocoloId) {
        return jpaRepository.findById(protocoloId);
    }

}
