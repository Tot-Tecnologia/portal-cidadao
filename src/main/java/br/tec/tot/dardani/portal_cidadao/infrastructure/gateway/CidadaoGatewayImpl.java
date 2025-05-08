package br.tec.tot.dardani.portal_cidadao.infrastructure.gateway;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.tec.tot.dardani.portal_cidadao.application.gateway.CidadaoGateway;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Cidadao;
import br.tec.tot.dardani.portal_cidadao.domain.repositories.CidadaoRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.repositories.FirebaseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CidadaoGatewayImpl implements CidadaoGateway {

    private final CidadaoRepository repository;
    private final FirebaseRepository firebaseRepository;

    @Override
    @Transactional(rollbackFor = { ApiException.class, RuntimeException.class })
    public Cidadao criarUsuario(Cidadao modelo) {

        var cidadao = repository.salvar(modelo);
        firebaseRepository.criarUsuario(cidadao);

        return cidadao;
    }

}
