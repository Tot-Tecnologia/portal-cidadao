package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.util.Collection;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.http.domain.TipoDocumento;
import lombok.Getter;

@Getter
public class ProtocoloCriadoResponse {

	private Long id;
	private String numeroProtocolo;
	private String cpfCnpj;
	private String telefone;
	private String nomeSolicitante;
	private String logradouro;
	private String numero;
	private String bairro;
	private String cep;
	private String estado;
	private String email;
	private String complemento;
	private String descricao;
	private String cidade;
	private String tipoDocumentoTexto;
	private Long tipoDocumentoId;
	private String statusTexto;
	private String statusEnum;
	private Collection<ArquivoCriadoResponse> documentos;
	private Collection<GuiaCriadaResponse> guias;

	public static ProtocoloCriadoResponse fromEntity(ProtocoloEntity entity) {
		var response = new ProtocoloCriadoResponse();

		response.id = entity.getId();
		response.descricao = entity.getDescricao();

		response.statusEnum = entity.getStatus().name();
		response.statusTexto = entity.getStatus().name();

		response.cep = entity.getEndereco().getCep();
		response.cpfCnpj = entity.getCpfCnpj();

		response.numero = entity.getEndereco().getNumero();
		response.bairro = entity.getEndereco().getBairro();
		response.logradouro = entity.getEndereco().getLogradouro();
		response.complemento = entity.getEndereco().getComplemento();

		response.nomeSolicitante = entity.getNomeSolicitante();
		response.numeroProtocolo = entity.getNumeroProtocolo();

		response.tipoDocumentoTexto = tipoDocumento(entity.getTipoDocumento());
		response.tipoDocumentoId = entity.getTipoDocumento();

		response.email = entity.getContato().getEmail();
		response.estado = entity.getEndereco().getCidade();
		response.cidade = entity.getEndereco().getEstado();
		response.telefone = entity.getContato().getTelefone();

		response.documentos = entity.getDocumentos().stream()
				.map((arq) -> new ArquivoCriadoResponse(arq.getId(), arq.getNomeOriginal())).toList();

		response.guias = entity.getGuias().stream()
				.map((arq) -> new GuiaCriadaResponse(
						arq.getId(),
						arq.getNomeOriginal(),
						arq.getStatus().name(),
						arq.getDataPagamento(),
						arq.getCriadoEm()))
				.toList();

		return response;

	}

	private static String tipoDocumento(Long tipoDocumentoId) {

		return TipoDocumento.buscarDocumentos().stream()
				.filter(td -> td.getId().equals(tipoDocumentoId))
				.findFirst().get().getNome();
	}

}
