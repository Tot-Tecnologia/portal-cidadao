package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProtocoloFiltrosRequest extends PaginacaoRequest {

	private Integer ano;
	private Long tipoDocumento;
	private String numeroProtocolo;
	private String cpfCnpj;

}