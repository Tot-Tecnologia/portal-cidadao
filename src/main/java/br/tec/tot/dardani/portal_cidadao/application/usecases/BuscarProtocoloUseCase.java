package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BuscarProtocoloUseCase {

    private final ProtocoloRepository repository;

    public ProtocoloCriadoResponse executar(String numeroProtocolo) {
        return repository.buscarProtocoloPorNumeroProtocolo(numeroProtocolo)
                .map(ProtocoloCriadoResponse::fromEntity)
                .orElseThrow(() -> new ApiException("Protocolo não encontrado", HttpStatus.NOT_FOUND));
    }

}
