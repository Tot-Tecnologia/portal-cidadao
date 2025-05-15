package br.tec.tot.dardani.portal_cidadao.infrastructure.controllers;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ArquivoRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ConsultaResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloCriadoResponse;
import br.tec.tot.dardani.portal_cidadao.application.dtos.response.ProtocoloFiltroResponse;
import br.tec.tot.dardani.portal_cidadao.application.mappers.DocumentoMapper;
import br.tec.tot.dardani.portal_cidadao.application.mappers.GuiaMapper;
import br.tec.tot.dardani.portal_cidadao.application.mappers.ProtocoloMapper;
import br.tec.tot.dardani.portal_cidadao.application.usecases.AdicionarDocumentosUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.AdicionarGuiasUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocoloUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.BuscarProtocolosUseCase;
import br.tec.tot.dardani.portal_cidadao.application.usecases.CriarProtocoloUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("protocolos")
public class ProtocoloController {

    private final ProtocoloMapper mapper;

    private final DocumentoMapper documentoMapper;
    private final GuiaMapper guiaMapper;

    private final CriarProtocoloUseCase criarProtocoloUseCase;
    private final BuscarProtocolosUseCase buscarProtocolosUseCase;
    private final BuscarProtocoloUseCase buscarProtocoloUseCase;
    private final AdicionarDocumentosUseCase adicionarDocumentosUseCase;
    private final AdicionarGuiasUseCase adicionarGuiasUseCase;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProtocoloCriadoResponse> criarProtocolo(
            @ModelAttribute ProtocoloRequest request) {

        var protocolo = mapper.toDomain(request);

        var protocoloResponse = this.criarProtocoloUseCase.executar(protocolo);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(protocoloResponse.getId())
                .toUri();

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

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/documentos/{id}")
    public ResponseEntity<ProtocoloCriadoResponse> adicionarArquivosAoProtocolo(@PathVariable Long id,
            @ModelAttribute ArquivoRequest request) {

        var arquivos = request.getDocumentos().stream().map(documentoMapper::toDomain).toList();

        var response = this.adicionarDocumentosUseCase.executar(arquivos, id);

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

    @PatchMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "/guias/{id}")
    public ResponseEntity<ProtocoloCriadoResponse> adicionarGuiasAoProtocolo(@PathVariable Long id,
            @ModelAttribute ArquivoRequest request) {

        var arquivos = request.getGuias().stream().map(guiaMapper::toDomain).toList();

        var response = this.adicionarGuiasUseCase.executar(arquivos, id);

        return ResponseEntity.status(HttpStatus.OK.value()).body(response);
    }

}
