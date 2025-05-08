package br.tec.tot.dardani.portal_cidadao.application.gateway;

import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;

public interface CidadaoGateway {

    Cidadao criarUsuario(Cidadao modelo);

}
