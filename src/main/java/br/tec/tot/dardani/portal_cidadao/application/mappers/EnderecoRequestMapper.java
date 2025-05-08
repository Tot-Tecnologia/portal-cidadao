package br.tec.tot.dardani.portal_cidadao.application.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cep;
import br.tec.tot.dardani.portal_cidadao.domain.models.CidadeEstado;
import br.tec.tot.dardani.portal_cidadao.domain.models.Endereco;
import br.tec.tot.dardani.portal_cidadao.domain.models.Localizacao;

@Component
public class EnderecoRequestMapper {
	public Endereco toDomain(ProtocoloRequest request) {
		return new Endereco(
				new Localizacao(
						request.getLogradouro(),
						request.getNumero(),
						request.getBairro(),
						request.getComplemento()),
				new CidadeEstado(
						request.getCidade(),
						request.getEstado()),
				new Cep(request.getCep()));
	}
}
