package br.tec.tot.dardani.portal_cidadao.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.tec.tot.dardani.portal_cidadao.application.annotations.SessionData;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocoloUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocolosUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.CriarProtocoloUseCase;
import br.tec.tot.dardani.portal_cidadao.infrastructure.security.model.Sessao;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("protocolos")
public class ProtocoloController {

    private final CriarProtocoloUseCase criarProtocoloUseCase;
    private final BuscarProtocolosUseCase buscarProtocolosUseCase;
    private final BuscarProtocoloUseCase buscarProtocoloUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProtocoloCriadoResponse> criarProtocolo(
            @ModelAttribute ProtocoloRequest request, @NotNull @SessionData Sessao sessao) {

        log.debug("Executando criarProtocolo ({})", request.getNomeSolicitante());
        var protocoloResponse = this.criarProtocoloUseCase.executar(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(protocoloResponse.id())
                .toUri();

        log.info("Protocolo criado com sucesso : {}", protocoloResponse.id());

        return ResponseEntity.created(location).body(protocoloResponse);
    }

    @GetMapping
    public ResponseEntity<ConsultaResponse<ProtocoloFiltroResponse>> buscarProtocolos(
            @ModelAttribute ProtocoloFiltrosRequest parametros) {

        var response = buscarProtocolosUseCase.executar(parametros);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ProtocoloCriadoResponse> buscarProtocoloPorId(@PathVariable Long id) {
        return ResponseEntity.ok(buscarProtocoloUseCase.executar(id));
    }

}
