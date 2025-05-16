package br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.validations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.tec.tot.dardani.portal_cidadao.application.dtos.annotations.impl.domain.FileMetadata;

class FileSizeValidatorTest {

    @Test
    @DisplayName("Deve retornar erro para arquivo que excede o limite de tamanho")
    void deveRetornarErroParaArquivoForaDoLimite() {
        long limiteBytes = 1 * 1024 * 1024;
        FileSizeValidator validator = new FileSizeValidator(limiteBytes);

        List<FileMetadata> arquivos = List.of(
                new FileMetadata("grande.pdf", 2 * 1024 * 1024, "application/pdf"));

        List<String> erros = validator.validate(arquivos);

        assertEquals(1, erros.size());
        assertTrue(erros.get(0).contains("excede o limite"));
        assertTrue(erros.get(0).contains("grande.pdf"));
    }

    @Test
    @DisplayName("Não deve retornar erro para arquivos dentro do limite de tamanho")
    void naoDeveRetornarErroParaArquivoValido() {
        long limiteBytes = 2 * 1024 * 1024;
        FileSizeValidator validator = new FileSizeValidator(limiteBytes);

        List<FileMetadata> arquivos = List.of(
                new FileMetadata("pequeno.png", 500 * 1024, "image/png"));

        List<String> erros = validator.validate(arquivos);

        assertTrue(erros.isEmpty(), "Não deveria haver erro para arquivo abaixo do limite");
    }

    @Test
    @DisplayName("Não deve retornar erro para lista de arquivos vazia")
    void naoDeveRetornarErroParaListaVazia() {
        FileSizeValidator validator = new FileSizeValidator(1024 * 1024);

        List<FileMetadata> arquivos = List.of();

        List<String> erros = validator.validate(arquivos);

        assertTrue(erros.isEmpty(), "Não deveria haver erro com lista vazia");
    }

    @Test
    @DisplayName("Deve aceitar arquivos exatamente no limite")
    void deveAceitarArquivoNoLimite() {
        long limiteBytes = 1 * 1024 * 1024;
        FileSizeValidator validator = new FileSizeValidator(limiteBytes);

        List<FileMetadata> arquivos = List.of(
                new FileMetadata("exato.jpg", limiteBytes, "image/jpeg"));

        List<String> erros = validator.validate(arquivos);

        assertTrue(erros.isEmpty(), "Arquivo no limite exato deve ser aceito");
    }
}
