package br.tec.tot.dardani.portal_cidadao.application.gateway;

import java.util.Optional;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;

public interface ProtocoloGateway {

    Protocolo criarProtocolo(Protocolo modelo);

    ConsultaResponse<ProtocoloFiltroResponse> buscarProtocolos(ProtocoloFiltrosRequest parametros);

    Optional<Protocolo> buscarProtocoloPorId(Long id);

}
