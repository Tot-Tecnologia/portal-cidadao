package br.tec.tot.dardani.portal_cidadao.infrastructure.security;

import java.io.IOException;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import br.tec.tot.dardani.portal_cidadao.infrastructure.security.model.Sessao;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FirebaseSecurityFilter extends OncePerRequestFilter {

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
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token não informado");
        }

        try {
            String token = authHeader.substring(BEARER_PREFIX.length());
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
            var sessao = new Sessao(decodedToken.getUid(), decodedToken.getEmail());
            request.setAttribute(Sessao.KEY, sessao);
            filterChain.doFilter(request, response);

        } catch (FirebaseAuthException e) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Token inválido");
        }
    }
}