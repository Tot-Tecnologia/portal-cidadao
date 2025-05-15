package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.domain.repositories.CidadaoRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.CidadaoEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.CidadaoJpaRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CidadaoRepositoryImpl implements CidadaoRepository {

    private final CidadaoJpaRepository jpaRepository;

    @Override
    public CidadaoEntity salvar(CidadaoEntity entidade) {
        return jpaRepository.save(entidade);
    }

}
