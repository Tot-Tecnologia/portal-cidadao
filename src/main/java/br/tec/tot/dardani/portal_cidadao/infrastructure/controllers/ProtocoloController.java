package br.tec.tot.dardani.portal_cidadao.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ArquivoRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.application.usecases.AdicionarArquivosUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocoloUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocolosUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.CriarProtocoloUseCase;
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
    private final AdicionarArquivosUseCase adicionarArquivoUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProtocoloCriadoResponse> criarProtocolo(
            @ModelAttribute ProtocoloRequest request) {

        log.debug("Executando criarProtocolo ({})", request.getNomeSolicitante());
        var protocoloResponse = this.criarProtocoloUseCase.executar(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(protocoloResponse.getId())
                .toUri();

        log.info("Protocolo criado com sucesso : {}", protocoloResponse.getId());

        return ResponseEntity.created(location).body(protocoloResponse);
    }

    @GetMapping
    public ResponseEntity<ConsultaResponse<ProtocoloFiltroResponse>> buscarProtocolos(
            @ModelAttribute ProtocoloFiltrosRequest parametros) {

        var response = buscarProtocolosUseCase.executar(parametros);
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{numeroProtocolo}")
    public ResponseEntity<ProtocoloCriadoResponse> buscarProtocoloPorId(@PathVariable String numeroProtocolo) {
        return ResponseEntity.ok(buscarProtocoloUseCase.executar(numeroProtocolo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProtocoloCriadoResponse> adicionarArquivosAoProtocolo(@PathVariable Long id,
            @ModelAttribute ArquivoRequest request) {

        var response = this.adicionarArquivoUseCase.executar(request, id);

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
