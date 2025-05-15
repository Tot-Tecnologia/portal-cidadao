package br.tec.tot.dardani.portal_cidadao.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;

public interface ProtocoloRepository {

    ProtocoloEntity salvar(ProtocoloEntity entity);

    Page<ProtocoloEntity> buscarProtocolos(ProtocoloFiltrosRequest parametros);

    Optional<ProtocoloEntity> buscarProtocoloPorNumeroProtocolo(String numeroProtocolo);

    Optional<ProtocoloEntity> buscarProtocoloPorId(Long protocoloId);

}
