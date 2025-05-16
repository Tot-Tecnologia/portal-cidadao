package br.tec.tot.dardani.portal_cidadao.domain.usecases;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ProtocoloNotFoundException;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocoloUseCase {

    private final ProtocoloRepository repository;

    public ProtocoloCriadoResponse executar(String numeroProtocolo) {
        return repository.buscarProtocoloPorNumeroProtocolo(numeroProtocolo)
                .map(ProtocoloCriadoResponse::fromEntity)
                .orElseThrow(() -> new ProtocoloNotFoundException());
    }

}
