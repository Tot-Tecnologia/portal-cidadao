package br.tec.tot.dardani.portal_cidadao.domain.usecases;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers.ProtocoloMapper;
import br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.service.SessaoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarProtocoloUseCase {

    private final ProtocoloMapper mapper;
    private final SessaoService sessaoService;
    private final ProtocoloRepository protocoloRepository;

    public ProtocoloCriadoResponse executar(ProtocoloRequest request) {

        var entitidade = mapper.toEntity(request);

        entitidade.setPessoa(sessaoService.getProprietario());
        entitidade.setNumeroProtocolo(gerarNumeroProtocolo());

        var protocoloSalvo = protocoloRepository.salvar(entitidade);

        return ProtocoloCriadoResponse.fromEntity(protocoloSalvo);
    }

    public String gerarNumeroProtocolo() {
        return "PR" + LocalDateTime.now().getNano();
    }

}