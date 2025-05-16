package br.tec.tot.dardani.portal_cidadao.domain.usecases;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocolosUseCase {

    private final ProtocoloRepository repository;

    public ConsultaResponse<ProtocoloFiltroResponse> executar(ProtocoloFiltrosRequest parametros) {
        return ConsultaResponse.of(repository
                .buscarProtocolos(parametros)
                .map(ProtocoloFiltroResponse::fromEntity));
    }

}
