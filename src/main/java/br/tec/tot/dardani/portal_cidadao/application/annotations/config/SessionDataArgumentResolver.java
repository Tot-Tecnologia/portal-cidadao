package br.tec.tot.dardani.portal_cidadao.application.annotations.config;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import br.tec.tot.dardani.portal_cidadao.application.annotations.SessionData;
import br.tec.tot.dardani.portal_cidadao.infrastructure.security.model.Sessao;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class SessionDataArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(SessionData.class) != null
                && parameter.getParameterType().equals(Sessao.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        return request.getAttribute(Sessao.KEY);
    }

}
