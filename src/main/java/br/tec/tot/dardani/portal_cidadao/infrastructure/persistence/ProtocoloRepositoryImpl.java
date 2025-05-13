
package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.ProtocoloFiltrosRequest;
import br.tec.tot.dardani.portal_cidadao.domain.models.Protocolo;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.ProtocoloRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.ProtocoloEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.mappers.ProtocoloMapper;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.ProtocoloJpaRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.service.SessaoDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProtocoloRepositoryImpl extends AbstractRepository implements ProtocoloRepository {

    private final ProtocoloMapper mapper;
    private final ProtocoloJpaRepository jpaRepository;
    private final SessaoDataService sessao;

    @Override
    public Protocolo salvar(Protocolo modelo) {

        log.debug("Executando salvar ({})", modelo.getNomeSolicitante());

        var entidade = mapper.toEntity(modelo);

        entidade.setPessoa(sessao.getProprietario());

        jpaRepository.save(entidade);

        log.info("Protocolo salvo no DB");

        return mapper.toModel(entidade);
    }

    @Override
    public Page<ProtocoloEntity> buscarProtocolos(ProtocoloFiltrosRequest parametros) {
        return jpaRepository.consultarProtocolos(parametros, parsePaginacao(parametros));
    }

    @Override
    public Optional<ProtocoloEntity> buscarProtocoloPorNumero(String numeroProtocolo) {
        return jpaRepository.findByNumeroProtocolo(numeroProtocolo);
    }

}
