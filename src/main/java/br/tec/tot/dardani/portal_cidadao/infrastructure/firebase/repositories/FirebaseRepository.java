package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.repositories;

import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import br.tec.tot.dardani.portal_cidadao.application.dtos.request.CidadaoRequest;
import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.config.FirebaseAuthErrorTranslator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FirebaseRepository {

    public void criarUsuario(CidadaoRequest modelo, Long id) {

        try {

            log.debug("Executando criarUsuario ({})", modelo);
            var usuarioRequest = new UserRecord.CreateRequest()
                    .setUid(String.valueOf(id))
                    .setDisplayName(modelo.nome())
                    .setEmail(modelo.email())
                    .setEmailVerified(false)
                    .setPassword(modelo.senha());

            FirebaseAuth.getInstance().createUser(usuarioRequest);

        } catch (FirebaseAuthException ex) {
            log.error("Falha ao criar usuario no firebase {} ", ex.getMessage());
            throw new ApiException(FirebaseAuthErrorTranslator.translate(ex));
        }
    }

}
