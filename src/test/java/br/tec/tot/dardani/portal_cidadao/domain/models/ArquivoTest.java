package br.tec.tot.dardani.portal_cidadao.domain.models;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.DomainException;

@DisplayName("Classe Arquivo")
class ArquivoTest {

    private static final byte[] CONTEUDO_VALIDO = new byte[] { 1, 2, 3 };
    private static final String NOME_VALIDO = "documento.pdf";
    private static final Long TAMANHO_VALIDO = 1024L;
    private static final TipoArquivoEnum TIPO_VALIDO = TipoArquivoEnum.DOCUMENTO;

    @Test
    @DisplayName("deve criar instância com parâmetros válidos")
    void criar_ComParametrosValidos_DeveRetornarInstancia() {

        Arquivo arquivo = Arquivo.criar(1L, NOME_VALIDO, "application/pdf", CONTEUDO_VALIDO, TAMANHO_VALIDO,
                TIPO_VALIDO);

        assertNotNull(arquivo);
        assertEquals(1L, arquivo.getId());
        assertEquals(NOME_VALIDO, arquivo.getNomeOriginal());
        assertEquals("application/pdf", arquivo.getMimeType());
        assertArrayEquals(CONTEUDO_VALIDO, arquivo.getConteudo());
        assertEquals(TAMANHO_VALIDO, arquivo.getTamanho());
        assertEquals(TIPO_VALIDO, arquivo.getTipoArquivo());
    }

    @Test
    @DisplayName("deve lançar exceção quando nome original for nulo")
    void criar_ComNomeOriginalNulo_DeveLancarExcecao() {

        DomainException exception = assertThrows(DomainException.class,
                () -> Arquivo.criar(1L, null, "application/pdf", CONTEUDO_VALIDO, TAMANHO_VALIDO, TIPO_VALIDO));

        assertEquals("Nome original não pode ser nulo", exception.getMessage());
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("deve lançar exceção quando conteúdo for nulo ou vazio")
    void criar_ComConteudoInvalido_DeveLancarExcecao(byte[] conteudoInvalido) {

        DomainException exception = assertThrows(DomainException.class,
                () -> Arquivo.criar(1L, NOME_VALIDO, "application/pdf", conteudoInvalido, TAMANHO_VALIDO, TIPO_VALIDO));

        assertEquals("Conteúdo do arquivo não pode ser vazio", exception.getMessage());
    }

    @Test
    @DisplayName("deve lançar exceção quando tamanho for nulo")
    void criar_ComTamanhoNulo_DeveLancarExcecao() {

        DomainException exception = assertThrows(DomainException.class,
                () -> Arquivo.criar(1L, NOME_VALIDO, "application/pdf", CONTEUDO_VALIDO, null, TIPO_VALIDO));

        assertEquals("Tamanho não pode ser nulo", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "image/png", "application/octet-stream", "text/plain" })
    @NullAndEmptySource
    @DisplayName("deve lançar exceção quando mimeType for inválido")
    void criar_ComMimeTypeInvalido_DeveLancarExcecao(String mimeTypeInvalido) {

        DomainException exception = assertThrows(DomainException.class,
                () -> Arquivo.criar(1L, NOME_VALIDO, mimeTypeInvalido, CONTEUDO_VALIDO, TAMANHO_VALIDO, TIPO_VALIDO));

        assertEquals("Somente arquivos PDF são permitidos", exception.getMessage());
    }

    @Test
    @DisplayName("deve lançar exceção quando tipoArquivo for nulo")
    void criar_ComTipoArquivoNulo_DeveLancarExcecao() {

        DomainException exception = assertThrows(DomainException.class,
                () -> Arquivo.criar(1L, NOME_VALIDO, "application/pdf", CONTEUDO_VALIDO, TAMANHO_VALIDO, null));

        assertEquals("Tipo do arquivo é obrigátorio", exception.getMessage());
    }

    @Test
    @DisplayName("getConteudo deve retornar cópia defensiva do array")
    void getConteudo_DeveRetornarCopiaDoConteudo() {

        byte[] conteudoOriginal = new byte[] { 1, 2, 3 };
        Arquivo arquivo = Arquivo.criar(1L, NOME_VALIDO, "application/pdf", conteudoOriginal, TAMANHO_VALIDO,
                TIPO_VALIDO);

        byte[] copiaConteudo = arquivo.getConteudo();

        assertArrayEquals(conteudoOriginal, copiaConteudo);
        assertNotSame(conteudoOriginal, copiaConteudo, "Deve retornar uma cópia do array, não o original");
    }

}