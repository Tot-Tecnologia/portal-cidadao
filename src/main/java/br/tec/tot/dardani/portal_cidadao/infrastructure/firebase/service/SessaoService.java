package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.tec.tot.dardani.portal_cidadao.application.security.model.Sessao;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.entities.PessoaEntity;
import br.tec.tot.dardani.portal_cidadao.infrastructure.data.persistence.repositories.PessoaJpaRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public final class SessaoService {

    private final PessoaJpaRepository repository;

    public PessoaEntity getProprietario() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        Sessao sessao = (Sessao) request.getAttribute(Sessao.KEY);

        if (sessao == null) {
            throw new ApiException("Sessão inválida");
        }

        return repository.findById(Long.valueOf(sessao.getUid()))
                .orElseThrow(() -> new ApiException("Usuário não encontrado", HttpStatus.NOT_FOUND));
    }
}
