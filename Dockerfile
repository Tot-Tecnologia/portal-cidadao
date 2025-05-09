FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/portal-cidadao-0.0.1.jar portal-cidadao-0.0.1.jar

RUN echo "$FIREBASE_ACCOUNT_VALUE" > /app/firebase-key.json || echo "Firebase config not set" > /app/firebase-key.json

EXPOSE 8080
CMD ["java", "-jar", "portal-cidadao-0.0.1.jar"]