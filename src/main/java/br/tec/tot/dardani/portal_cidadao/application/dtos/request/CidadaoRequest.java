package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CidadaoRequest(
                @NotBlank(message = "O CPF/CNPJ é obrigatorio") @Pattern(regexp = "^(\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}|\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2})$", message = "CPF ou CNPJ com formato inválido") String cpfCnpj,
                @NotBlank(message = "O Nome é inválido") String nome,
                @Pattern(regexp = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-\\d{4}$", message = "Telefone inválido") @NotBlank String telefone,
                @NotBlank(message = "O e-mail é obrigatório") @Email(message = "E-mail inválido") String email,
                @NotBlank(message = "A senha é inválida") String senha) {

}
