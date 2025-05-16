package br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.embeddables.ContatoEmbeddable;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.embeddables.EnderecoEmbeddable;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.enums.ProtocoloStatusEnum;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProtocoloMapper extends NormalizeFields {

	private final DocumentoMapper documentoMapper;

	public ProtocoloEntity toEntity(ProtocoloRequest request) {
		var entity = new ProtocoloEntity();

		entity.setContato(new ContatoEmbeddable(normalizeField(request.getTelefone()), request.getEmail()));
		entity.setEndereco(new EnderecoEmbeddable(
				request.getLogradouro(),
				request.getNumero(),
				request.getBairro(),
				request.getComplemento(),
				request.getCidade(),
				request.getEstado(),
				normalizeField(request.getCep())));
		entity.setNomeSolicitante(request.getNomeSolicitante());
		entity.setDescricao(request.getDescricao());
		entity.setStatus(ProtocoloStatusEnum.EM_ANALISE);
		entity.setTipoDocumento(request.getTipoDocumento());
		entity.setCpfCnpj(normalizeField(request.getCpfCnpj()));

		request.getDocumentos().stream()
				.map(documentoMapper::toEntity)
				.peek(doc -> doc.setProtocolo(entity))
				.forEach(entity::adicionarDocumento);

		return entity;

	}

}
