package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.config;

import java.io.InputStream;

import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

    @PostConstruct
    public void initialization() {
        try {
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase/firebase-key.json");

            if (serviceAccount == null) {
                throw new RuntimeException("Chave do Firebase não encontrada no classpath.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);

            log.info("Firebase inicializado com sucesso");
        } catch (Exception error) {
            log.error("Erro ao configurar Firebase", error);
            throw new RuntimeException("Falha ao configurar firebase");
        }
    }
}
