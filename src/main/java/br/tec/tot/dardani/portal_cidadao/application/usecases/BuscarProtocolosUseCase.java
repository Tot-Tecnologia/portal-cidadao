package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocolosUseCase {

    private final ProtocoloGateway gateway;

    public ConsultaResponse<ProtocoloFiltroResponse> executar(ProtocoloFiltrosRequest parametros) {
        return gateway.buscarProtocolos(parametros);
    }

}
