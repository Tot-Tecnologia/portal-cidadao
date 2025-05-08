package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import br.tec.tot.dardani.portal_cidadao.application.mappers.ProtocoloApplicationMapper;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocoloUseCase {

    private final ProtocoloGateway gateway;
    private final ProtocoloApplicationMapper mapper;

    public ProtocoloCriadoResponse executar(Long id) {
        return gateway.buscarProtocoloPorId(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ApiException("Protocolo não encontrado", HttpStatus.NOT_FOUND));
    }

}
