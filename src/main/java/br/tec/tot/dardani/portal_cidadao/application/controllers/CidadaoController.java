package br.tec.tot.dardani.portal_cidadao.application.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.CidadaoRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.CidadaoCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.domain.usecases.CriarCidadaoUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class CidadaoController {

	private final CriarCidadaoUseCase criarCidadaoUseCase;

	@PostMapping(path = "registrar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CidadaoCriadoResponse> criarProtocolo(
			@RequestBody CidadaoRequest request) {

		var response = this.criarCidadaoUseCase.executar(request);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(response);
	}

}
