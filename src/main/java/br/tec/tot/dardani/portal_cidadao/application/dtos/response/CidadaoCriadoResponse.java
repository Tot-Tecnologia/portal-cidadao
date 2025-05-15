package br.tec.tot.dardani.portal_cidadao.application.dtos.response;

import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.CidadaoEntity;
import lombok.Getter;

@Getter
public class CidadaoCriadoResponse {
    private String cpfCnpj;
    private String nome;
    private String telefone;
    private String email;

    public static CidadaoCriadoResponse fromEntity(CidadaoEntity entity) {

        var response = new CidadaoCriadoResponse();

        response.cpfCnpj = entity.getDocumento();
        response.nome = entity.getNome();
        response.telefone = entity.getContato().getTelefone();
        response.email = entity.getContato().getEmail();

        return response;
    }
}
