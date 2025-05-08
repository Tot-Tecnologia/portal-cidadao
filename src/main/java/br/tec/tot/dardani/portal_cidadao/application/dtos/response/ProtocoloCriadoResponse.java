package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import java.util.Collection;

public record ProtocoloCriadoResponse(
        Long id,

        String cpfCnpj,
        String telefone,
        String nomeSolicitante,
        String logradouro,
        String numero,
        String bairro,
        String cep,
        String estado,
        String email,
        String complemento,
        String descricao,
        String cidade,
        Long tipoDocumento,
        String status,
        Collection<ArquivoCriadoResponse> arquivos) {

}
