package br.tec.tot.dardani.portal_cidadao.application.usecases;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.mappers.GuiaMapper;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Guia;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdicionarGuiasUseCase {

    private final GuiaMapper mapper;
    private final ProtocoloRepository repository;

    public ProtocoloCriadoResponse executar(List<Guia> guias, Long protocoloId) {

        var protocoloEntity = repository
                .buscarProtocoloPorId(protocoloId)
                .orElseThrow(() -> new ApiException("Protocolo não encontrado", HttpStatus.NOT_FOUND));

        guias
                .stream()
                .map(mapper::toEntity)
                .peek(arq -> arq.setProtocolo(protocoloEntity))
                .forEach(protocoloEntity::adicionarGuia);

        var protocoloAtualizado = repository.salvar(protocoloEntity);

        return ProtocoloCriadoResponse.fromEntity(protocoloAtualizado);
    }
}
