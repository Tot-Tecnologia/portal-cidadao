package br.tec.tot.dardani.portal_cidadao.domain.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;

public interface ProtocoloRepository {

    Protocolo salvar(Protocolo modelo);

    Page<ProtocoloEntity> buscarProtocolos(ProtocoloFiltrosRequest parametros);

    Optional<ProtocoloEntity> buscarProtocoloPorNumero(String numeroProtocolo);

}
