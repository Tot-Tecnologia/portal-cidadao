package br.tec.tot.dardani.portal_cidadao.application.dtos.request;

public record CidadaoRequest(
        String cpfCnpj,
        String nome,
        String telefone,
        String email,
        String senha) {

}
