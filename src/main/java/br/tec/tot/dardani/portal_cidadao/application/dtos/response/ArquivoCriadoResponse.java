package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.time.LocalDateTime;

public record ArquivoCriadoResponse(
		Long id,
		String nome,
		LocalDateTime dataCriacao) {
}