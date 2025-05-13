package br.tec.tot.dardani.portal_cidadao.application.annotations.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SessionDataArgumentResolver sessionDataArgumentResolver;

    public WebConfig(SessionDataArgumentResolver sessionDataArgumentResolver) {
        this.sessionDataArgumentResolver = sessionDataArgumentResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(sessionDataArgumentResolver);
    }
}