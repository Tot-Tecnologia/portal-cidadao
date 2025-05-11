package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.util.Collection;

import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.domain.models.TipoDocumento;
import lombok.Getter;

@Getter
public class ProtocoloCriadoResponse {

    private Long id;
    private String numeroProtocolo;
    private String cpfCnpj;
    private String telefone;
    private String nomeSolicitante;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String estado;
    private String email;
    private String complemento;
    private String descricao;
    private String cidade;
    private String tipoDocumentoTexto;
    private Long tipoDocumentoId;
    private String statusTexto;
    private String statusEnum;
    private Collection<ArquivoCriadoResponse> arquivos;

    public static ProtocoloCriadoResponse fromModel(Protocolo protocolo) {
        var response = new ProtocoloCriadoResponse();

        response.id = protocolo.getId();
        response.descricao = protocolo.getDescricao();

        response.statusEnum = protocolo.getStatus().name();
        response.statusTexto = protocolo.getStatus().getDescricao();

        response.cep = protocolo.getEndereco().getCep().getValor();
        response.cpfCnpj = protocolo.getCpfCnpj().getValor();

        response.numero = protocolo.getEndereco().getLocalizacao().getNumero();
        response.bairro = protocolo.getEndereco().getLocalizacao().getBairro();
        response.logradouro = protocolo.getEndereco().getLocalizacao().getLogradouro();
        response.complemento = protocolo.getEndereco().getLocalizacao().getComplemento();

        response.nomeSolicitante = protocolo.getNomeSolicitante();
        response.numeroProtocolo = protocolo.getNumeroProtocolo();

        response.tipoDocumentoTexto = tipoDocumento(protocolo);
        response.tipoDocumentoId = protocolo.getTipoDocumento();

        response.email = protocolo.getContato().getEmail();
        response.estado = protocolo.getEndereco().getCidadeEstado().getCidade();
        response.cidade = protocolo.getEndereco().getCidadeEstado().getEstado();
        response.telefone = protocolo.getContato().getTelefone();

        response.arquivos = protocolo.getArquivos().stream()
                .map((arq) -> new ArquivoCriadoResponse(arq.getId(), arq.getNomeOriginal())).toList();

        return response;

    }

    private static String tipoDocumento(Protocolo protocolo) {

        return TipoDocumento.buscarDocumentos().stream()
                .filter(td -> td.getId().equals(protocolo.getTipoDocumento()))
                .findFirst().get().getNome();
    }

}
