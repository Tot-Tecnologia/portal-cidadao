package br.tec.tot.dardani.portal_cidadao.application.usecases;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.mappers.DocumentoMapper;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Documento;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdicionarDocumentosUseCase {

	private final DocumentoMapper mapper;
	private final ProtocoloRepository repository;

	public ProtocoloCriadoResponse executar(List<Documento> documentos, Long protocoloId) {

		var protocoloEntity = repository
				.buscarProtocoloPorId(protocoloId)
				.orElseThrow(() -> new ApiException("Protocolo não encontrado", HttpStatus.NOT_FOUND));

		documentos
				.stream()
				.map(mapper::toEntity)
				.peek(arq -> arq.setProtocolo(protocoloEntity))
				.forEach(protocoloEntity::adicionarDocumento);

		var protocoloAtualizado = repository.salvar(protocoloEntity);

		return ProtocoloCriadoResponse
				.fromEntity(protocoloAtualizado);

	}

}
