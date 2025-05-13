package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import java.util.Collection;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocoloRequest {

    private String cpfCnpj;
    private String telefone;
    private String nomeSolicitante;
    private String endereco;
    private String logradouro;
    private String numero;
    private String bairro;
    private String cep;
    private String estado;
    private String email;
    private String complemento;
    private String descricao;
    private String cidade;
    private Long tipoDocumento;
    private Collection<MultipartFile> arquivos;

    public Collection<MultipartFile> getArquivos() {
        if (arquivos == null) {
            return List.of();
        }
        return arquivos;
    }
}