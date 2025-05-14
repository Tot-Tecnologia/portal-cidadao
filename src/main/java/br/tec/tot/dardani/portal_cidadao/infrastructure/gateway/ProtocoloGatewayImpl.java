package br.tec.tot.dardani.portal_cidadao.infrastructure.gateway;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.application.gateway.ProtocoloGateway;
import br.tec.tot.dardani.portal_cidadao.domain.models.Arquivo;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers.ProtocoloMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProtocoloGatewayImpl implements ProtocoloGateway {

    private final ProtocoloRepository repository;
    private final ProtocoloMapper mapper;

    @Override
    public Protocolo criarProtocolo(Protocolo modelo) {
        return repository.salvar(modelo);
    }

    @Override
    public ConsultaResponse<ProtocoloFiltroResponse> buscarProtocolos(ProtocoloFiltrosRequest parametros) {
        Page<ProtocoloEntity> page = repository.buscarProtocolos(parametros);

        Page<ProtocoloFiltroResponse> pageResponse = page.map(protocolo -> new ProtocoloFiltroResponse(
                protocolo.getId(),
                protocolo.getNumeroProtocolo(),
                protocolo.getTipoDocumento(),
                protocolo.getCriadoEm(),
                protocolo.getStatus().name(),
                protocolo.getStatus().getDescricao()));

        return ConsultaResponse.of(pageResponse);
    }

    @Override
    public Optional<Protocolo> buscarProtocoloPorId(String numeroProtocolo) {
        return this.repository.buscarProtocoloPorNumero(numeroProtocolo).map(mapper::toModel);
    }

    @Override
    public Protocolo adicionarArquivosAoProtocolo(List<Arquivo> arquivos, Long protocoloId) {
        return this.repository.adicionarArquivosAoProtocolo(arquivos, protocoloId);
    }

}