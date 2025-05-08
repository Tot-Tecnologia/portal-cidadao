package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.domain.models.Arquivo;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ArquivoRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers.ArquivoMapper;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.ArquivoJpaRepository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ArquivoRepositoryImpl implements ArquivoRepository {

    private final ArquivoJpaRepository repository;
    private final ArquivoMapper mapper;

    @Override
    public Arquivo salvar(Arquivo modelo) {
        var entidade = repository.save(mapper.toEntity(modelo));
        return mapper.toModel(entidade);
    }

}
