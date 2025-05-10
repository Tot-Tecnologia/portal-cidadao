FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

ARG FIREBASE_ACCOUNT_VALUE

COPY target/portal-cidadao-0.0.1.jar portal-cidadao-0.0.1.jar
COPY firebase-key.json firebase-key.json

EXPOSE 8080
CMD ["java", "-jar", "portal-cidadao-0.0.1.jar"]