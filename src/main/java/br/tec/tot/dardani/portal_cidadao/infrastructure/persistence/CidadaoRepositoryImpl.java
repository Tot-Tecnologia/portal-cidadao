package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.CidadaoRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers.CidadaoMapper;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.CidadaoJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CidadaoRepositoryImpl implements CidadaoRepository {

    private final CidadaoJpaRepository jpaRepository;
    private final CidadaoMapper mapper;

    @Override
    public Cidadao salvar(Cidadao modelo) {
        var entidade = jpaRepository.save(mapper.toEntity(modelo));
        return mapper.toModel(entidade);
    }

}
