package br.tec.tot.dardani.portal_cidadao.domain.usecases;

import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ArquivoRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ProtocoloNotFoundException;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers.DocumentoMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdicionarDocumentosUseCase {

	private final DocumentoMapper mapper;
	private final ProtocoloRepository repository;

	public ProtocoloCriadoResponse executar(ArquivoRequest request, Long protocoloId) {

		var protocoloEntity = repository
				.buscarProtocoloPorId(protocoloId)
				.orElseThrow(() -> new ProtocoloNotFoundException());

		request.getDocumentos()
				.stream()
				.map(mapper::toEntity)
				.peek(arq -> arq.setProtocolo(protocoloEntity))
				.forEach(protocoloEntity::adicionarDocumento);

		var protocoloAtualizado = repository.salvar(protocoloEntity);

		return ProtocoloCriadoResponse
				.fromEntity(protocoloAtualizado);

	}

}
