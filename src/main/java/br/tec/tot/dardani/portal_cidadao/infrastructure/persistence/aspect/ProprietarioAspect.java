package br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import br.tec.tot.dardani.portal_cidadao.infrastructure.security.model.Sessao;
import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Aspect
@Configuration
@RequiredArgsConstructor
public class ProprietarioAspect {

    private final EntityManager manager;

    @Before("execution(* br.tec.tot.dardani.portal_cidadao.infrastructure.persistence.repositories.ProtocoloJpaRepository.*(..))")
    public void setProprietarioNaConsulta() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Sessao sessao = (Sessao) request.getAttribute(Sessao.KEY);

        var session = manager.unwrap(Session.class);
        session.enableFilter("proprietario").setParameter("pessoaId", Long.valueOf(sessao.getUid()));

    }

}