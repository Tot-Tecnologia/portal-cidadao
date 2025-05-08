package br.tec.tot.dardani.portal_cidadao.application.mappers;

import org.springframework.stereotype.Component;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Contato;

@Component
public class ContatoRequestMapper {

    public Contato toDomain(ProtocoloRequest request) {
        return new Contato(request.getTelefone(), request.getEmail());
    }

}
