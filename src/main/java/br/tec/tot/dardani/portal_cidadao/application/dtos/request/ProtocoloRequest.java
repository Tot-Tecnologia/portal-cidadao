package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProtocoloRequest extends ArquivoRequest {

    @NotBlank(message = "O CPF/CNPJ é obrigatorio")
    @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$", message = "CPF ou CNPJ com formato inválido")
    private String cpfCnpj;

    @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}$", message = "Telefone inválido")
    @NotBlank
    private String telefone;

    @NotBlank(message = "O nome do solicitante é inválido")
    private String nomeSolicitante;

    @NotBlank(message = "O logradouro é inválido")
    private String logradouro;

    @NotBlank(message = "O número é inválido")
    private String numero;

    @NotBlank(message = "O bairro é inválido")
    private String bairro;

    @NotBlank(message = "O CEP é inválido")
    private String cep;

    @NotBlank(message = "O Estado é inválido")
    private String estado;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;
    @NotBlank
    private String complemento;
    @NotBlank
    private String descricao;
    @NotBlank
    private String cidade;
    @NotNull
    private Long tipoDocumento;

}