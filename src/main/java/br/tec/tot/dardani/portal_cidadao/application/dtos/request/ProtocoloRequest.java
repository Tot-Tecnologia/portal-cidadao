package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocoloRequest extends ArquivoRequest {

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

}