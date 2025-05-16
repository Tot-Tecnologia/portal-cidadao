package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

class FileTotalSizeValidatorTest {

    @Test
    @DisplayName("Deve retornar erro quando o tamanho total dos arquivos excede o limite permitido")
    void deveRetornarErroQuandoTamanhoTotalExcedeOLimite() {

        FileTotalSizeValidator validator = new FileTotalSizeValidator(1.0);

        List<FileMetadata> files = List.of(
                new FileMetadata("arquivo1.txt", 800 * 1024, "text/plain"),
                new FileMetadata("arquivo2.txt", 400 * 1024, "text/plain"));

        List<String> errors = validator.validate(files);

        assertFalse(errors.isEmpty(), "Esperado erro de validação por exceder limite");
        assertTrue(errors.get(0).contains("excede o limite permitido"));
    }

    @Test
    @DisplayName("Não deve retornar erro quando o tamanho total dos arquivos está dentro do limite permitido")
    void naoDeveRetornarErroQuandoTamanhoTotalEstaDentroDoLimite() {
        FileTotalSizeValidator validator = new FileTotalSizeValidator(2.0);

        List<FileMetadata> files = List.of(
                new FileMetadata("arquivo1.txt", 700 * 1024, "text/plain"),
                new FileMetadata("arquivo2.txt", 900 * 1024, "text/plain"));

        List<String> errors = validator.validate(files);

        assertTrue(errors.isEmpty(), "Não deveria haver erros de validação");
    }

    @Test
    @DisplayName("Não deve retornar erro quando a lista de arquivos está vazia")
    void naoDeveRetornarErroParaListaVazia() {
        FileTotalSizeValidator validator = new FileTotalSizeValidator(1.0);
        List<FileMetadata> files = List.of();

        List<String> errors = validator.validate(files);

        assertTrue(errors.isEmpty(), "Não deveria haver erro para lista vazia");
    }
}
