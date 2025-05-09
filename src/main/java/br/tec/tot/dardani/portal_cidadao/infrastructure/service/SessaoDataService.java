package br.tec.tot.dardani.portal_cidadao.infrastructure.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.entities.PessoaEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.PessoaJpaRepository;
import br.tec.tot.dardani.portal_cidadao.infrastructure.security.model.Sessao;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class SessaoDataService {

    private final PessoaJpaRepository repository;

    public Optional<PessoaEntity> getPessoa() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Sessao sessao = (Sessao) request.getAttribute(Sessao.KEY);

        return repository.findById(Long.valueOf(sessao.getUid()));
    }
}
