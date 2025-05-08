package br.tec.tot.dardani.portal_cidadao.infrastructure.controllers;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.tec.tot.dardani.portal_cidadao.domain.models.TipoDocumento;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("tipos-documento")
@RequiredArgsConstructor
public class TipoDocumentoController {

    @GetMapping
    public ResponseEntity<Collection<TipoDocumento>> buscarTiposDocumento() {
        return ResponseEntity.ok(TipoDocumento.buscarDocumentos());
    }

}
