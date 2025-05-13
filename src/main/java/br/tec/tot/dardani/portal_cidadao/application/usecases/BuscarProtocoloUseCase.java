package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocoloUseCase {

    private final ProtocoloGateway gateway;

    public ProtocoloCriadoResponse executar(String numeroProtocolo) {
        return gateway.buscarProtocoloPorId(numeroProtocolo)
                .map(ProtocoloCriadoResponse::fromModel)
                .orElseThrow(() -> new ApiException("Protocolo não encontrado", HttpStatus.NOT_FOUND));
    }

}
