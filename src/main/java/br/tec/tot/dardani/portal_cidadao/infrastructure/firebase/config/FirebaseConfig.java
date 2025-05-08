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
    private String pathAccountKey;

    @PostConstruct
    public void initialization() {
        try {

            FileInputStream serviceAccount = new FileInputStream(pathAccountKey);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception error) {
            throw new RuntimeException("Falha ao configurar firebase");
        }
    }

}
