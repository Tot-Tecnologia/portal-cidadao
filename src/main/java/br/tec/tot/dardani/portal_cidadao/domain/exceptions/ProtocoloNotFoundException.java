package br.tec.tot.dardani.portal_cidadao.domain.exceptions;

import org.springframework.http.HttpStatus;

public class ProtocoloNotFoundException extends DomainException {

    public ProtocoloNotFoundException() {
        super("Protocolo não encontrado", HttpStatus.NOT_FOUND);
    }

}
