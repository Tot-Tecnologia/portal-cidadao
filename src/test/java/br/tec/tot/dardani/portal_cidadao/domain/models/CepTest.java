package br.tec.tot.dardani.portal_cidadao.domain.models;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Classe Cep")
class CepTest {

    @Test
    @DisplayName("deve criar instância com CEP válido sem hífen")
    void deveCriarInstanciaComCepValidoSemHifen() {
        Cep cep = new Cep("12345678");

        assertNotNull(cep);
        assertEquals("12345678", cep.getValor());
    }

    @Test
    @DisplayName("deve criar instância com CEP válido com hífen")
    void deveCriarInstanciaComCepValidoComHifen() {
        Cep cep = new Cep("12345-678");

        assertNotNull(cep);
        assertEquals("12345678", cep.getValor());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = { "", " ", "    " })
    @DisplayName("deve lançar exceção quando CEP for nulo ou vazio")
    void deveLancarExcecaoQuandoCepForNuloOuVazio(String cepInvalido) {
        DomainException exception = assertThrows(DomainException.class,
                () -> new Cep(cepInvalido));

        assertEquals("CEP não pode ser nulo ou vazio", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "1234567", "123456789", "123" })
    @DisplayName("deve lançar exceção quando CEP não tiver 8 dígitos após limpeza")
    void deveLancarExcecaoQuandoCepNaoTiver8Digitos(String cepInvalido) {
        DomainException exception = assertThrows(DomainException.class,
                () -> new Cep(cepInvalido));

        assertEquals("CEP deve conter 8 dígitos", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "123-45678", "123456-78", "12-345678", "1234-5678" })
    @DisplayName("deve lançar exceção quando hífen estiver na posição incorreta")
    void deveLancarExcecaoQuandoHifenEstiverIncorreto(String cepInvalido) {
        DomainException exception = assertThrows(DomainException.class,
                () -> new Cep(cepInvalido));

        assertEquals("Formato de CEP inválido. Use o padrão 12345-678", exception.getMessage());
    }

    @Test
    @DisplayName("deve aceitar CEP com zeros à esquerda")
    void deveAceitarCepComZerosAEsquerda() {
        Cep cep = new Cep("00123-456");

        assertEquals("00123456", cep.getValor());
    }

    @Test
    @DisplayName("deve aceitar CEP com todos os dígitos iguais")
    void deveAceitarCepComTodosDigitosIguais() {
        Cep cep = new Cep("99999-999");

        assertEquals("99999999", cep.getValor());
    }

    @Test
    @DisplayName("deve lançar exceção quando não houver dígitos suficientes após limpeza")
    void deveLancarExcecaoQuandoNaoHouverDigitosSuficientes() {
        assertThrows(DomainException.class,
                () -> new Cep("ABC-DEFG"));
    }
}