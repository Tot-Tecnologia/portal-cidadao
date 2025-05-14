package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ArquivoRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import br.tec.tot.dardani.portal_cidadao.application.mappers.ArquivoRequestMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdicionarArquivosUseCase {

    private final ArquivoRequestMapper arquivoMapper;
    private final ProtocoloGateway gateway;

    public ProtocoloCriadoResponse executar(ArquivoRequest request, Long protocoloId) {

        var arquivos = request
                .getArquivos()
                .stream()
                .map(arquivoMapper::toDomain).toList();

        var protocoloAtualizado = gateway.adicionarArquivosAoProtocolo(arquivos, protocoloId);

        return ProtocoloCriadoResponse
                .fromModel(protocoloAtualizado);

    }

}
