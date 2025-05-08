package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import br.tec.tot.dardani.portal_cidadao.application.mappers.ProtocoloApplicationMapper;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarProtocoloUseCase {

    private final ProtocoloGateway protocoloGateway;
    private final ProtocoloApplicationMapper mapper;

    public ProtocoloCriadoResponse executar(ProtocoloRequest request) {
        Protocolo protocolo = mapper.toDomain(request);

        Protocolo novoProtocolo = protocoloGateway.criarProtocolo(protocolo);

        return mapper.toResponse(novoProtocolo);
    }

}