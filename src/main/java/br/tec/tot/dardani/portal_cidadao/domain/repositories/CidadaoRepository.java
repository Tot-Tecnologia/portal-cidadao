package br.tec.tot.dardani.portal_cidadao.domain.repositories;

import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.CidadaoEntity;

public interface CidadaoRepository {

    CidadaoEntity salvar(CidadaoEntity entity);

}
