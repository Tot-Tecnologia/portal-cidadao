package br.tec.tot.dardani.portal_cidadao.infrastructure.firebase.config;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${firebase.pathAccountKey}")
    private String serviceAccountPath;

    @PostConstruct
    public void initialization() {
        try {
            log.info("PATH {}", serviceAccountPath);
            FileInputStream serviceAccount = new FileInputStream(serviceAccountPath);

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
