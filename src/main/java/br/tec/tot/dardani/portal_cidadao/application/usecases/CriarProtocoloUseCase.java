package br.tec.tot.dardani.portal_cidadao.application.usecases;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.mappers.ProtocoloMapper;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.service.SessaoService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CriarProtocoloUseCase {

    private final ProtocoloMapper mapper;
    private final SessaoService sessaoService;
    private final ProtocoloRepository protocoloRepository;

    public ProtocoloCriadoResponse executar(Protocolo modelo) {

        var entitidade = mapper.toEntity(modelo);

        entitidade.setPessoa(sessaoService.getProprietario());

        var protocoloSalvo = protocoloRepository.salvar(entitidade);

        return ProtocoloCriadoResponse.fromEntity(protocoloSalvo);
    }

}