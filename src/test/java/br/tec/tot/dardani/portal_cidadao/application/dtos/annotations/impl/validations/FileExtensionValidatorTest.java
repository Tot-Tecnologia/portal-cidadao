package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

class FileExtensionValidatorTest {

    @Test
    @DisplayName("Deve retornar erro quando arquivo possui extensão não permitida")
    void deveRetornarErroParaExtensaoNaoPermitida() {
        FileExtensionValidator validator = new FileExtensionValidator(Set.of("application/pdf", "image/png"));

        List<FileMetadata> files = List.of(
                new FileMetadata("documento.pdf", 100_000, "application/pdf"),
                new FileMetadata("imagem.bmp", 100_000, "image/bmp") // não permitido
        );

        List<String> errors = validator.validate(files);

        assertEquals(1, errors.size(), "Deveria haver 1 erro de extensão");
        assertTrue(errors.get(0).contains("tem extensão não permitida"));
        assertTrue(errors.get(0).contains("imagem.bmp"));
    }

    @Test
    @DisplayName("Não deve retornar erro quando todas as extensões são permitidas")
    void naoDeveRetornarErroParaExtensoesPermitidas() {
        FileExtensionValidator validator = new FileExtensionValidator(Set.of("application/pdf", "image/png"));

        List<FileMetadata> files = List.of(
                new FileMetadata("doc.pdf", 100_000, "application/pdf"),
                new FileMetadata("foto.png", 100_000, "image/png"));

        List<String> errors = validator.validate(files);

        assertTrue(errors.isEmpty(), "Não deveria haver erros para extensões válidas");
    }

    @Test
    @DisplayName("Não deve retornar erro quando a lista de arquivos está vazia")
    void naoDeveRetornarErroParaListaVazia() {
        FileExtensionValidator validator = new FileExtensionValidator(Set.of("application/pdf"));
        List<FileMetadata> files = List.of(); // lista vazia

        List<String> errors = validator.validate(files);

        assertTrue(errors.isEmpty(), "Não deveria haver erro para lista vazia");
    }

    @Test
    @DisplayName("Deve aceitar contentType com letras maiúsculas (case insensitive)")
    void deveAceitarContentTypeCaseInsensitive() {
        FileExtensionValidator validator = new FileExtensionValidator(Set.of("APPLICATION/PDF", "IMAGE/PNG"));

        List<FileMetadata> files = List.of(
                new FileMetadata("arquivo1.pdf", 100_000, "Application/Pdf"),
                new FileMetadata("imagem.png", 100_000, "IMAGE/PNG"));

        List<String> errors = validator.validate(files);

        assertTrue(errors.isEmpty(), "Tipos com letras maiúsculas/minúsculas devem ser aceitos");
    }
}
