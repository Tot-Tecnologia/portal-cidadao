FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/portal-cidadao-0.0.1.jar /app/portal-cidadao-0.0.1.jar
COPY firebase-key.json /app/firebase-key.json

EXPOSE 8080
CMD ["java", "-jar", "portal-cidadao-0.0.1.jar"]