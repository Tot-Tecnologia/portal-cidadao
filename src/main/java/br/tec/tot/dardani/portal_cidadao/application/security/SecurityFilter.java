package br.tec.tot.dardani.portal_cidadao.application.security;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import br.tec.tot.dardani.portal_cidadao.application.security.exception.InvalidTokenExeception;
import br.tec.tot.dardani.portal_cidadao.application.security.model.Sessao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private HandlerExceptionResolver handlerExceptionResolver;

    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    private static final Set<String> WHITE_LIST = Set.of(
            "/portal-cidadao/auth/registrar");

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        return WHITE_LIST.contains(path)
                || path.startsWith("/portal-cidadao/swagger-ui")
                || path.startsWith("/portal-cidadao/v3/api-docs");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader(AUTH_HEADER);

        if (authHeader == null || !authHeader.startsWith(BEARER_PREFIX)) {
            handlerExceptionResolver.resolveException(request, response, null,
                    new InvalidTokenExeception("Token não informado"));
            return;
        }

        try {
            String token = authHeader.substring(BEARER_PREFIX.length());
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            var sessao = new Sessao(Long.valueOf(decodedToken.getUid()), decodedToken.getEmail());
            request.setAttribute(Sessao.KEY, sessao);
            filterChain.doFilter(request, response);

        } catch (FirebaseAuthException e) {
            handlerExceptionResolver.resolveException(request, response, null,
                    new InvalidTokenExeception("Token inválido"));
        }
    }
}