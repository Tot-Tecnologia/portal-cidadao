package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.repositories;

import org.springframework.stereotype.Component;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

import br.tec.tot.dardani.portal_cidadao.domain.exceptions.ApiException;
import br.tec.tot.dardani.portal_cidadao.domain.models.Usuario;
import br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.config.FirebaseAuthErrorTranslator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FirebaseRepository {

    public Usuario criarUsuario(Usuario modelo) {
        try {

            log.debug("Executando criarUsuario ({})", modelo);
            var usuarioRequest = new UserRecord.CreateRequest()
                    // .setUid(modelo.getId().toString())
                    .setDisplayName(modelo.getNome())
                    .setEmail(modelo.getLogin())
                    .setEmailVerified(false)
                    .setPassword(modelo.getSenha());

            FirebaseAuth.getInstance().createUser(usuarioRequest);

            log.info("Usuario criado no firebase: {}", modelo.getId());

            return modelo;

        } catch (FirebaseAuthException ex) {
            log.error("Falha ao criar usuario no firebase {} ", ex.getMessage());
            throw new ApiException(FirebaseAuthErrorTranslator.translate(ex));
        }
    }

}
