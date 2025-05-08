
# Executar docker
- docker-compose up -d

# Exportar variaveis

- As variaveis .env devem estar exportadas no servidor com seus respectivos valores

# Executar migracao
- mvn flyway:migrate -Dflyway.configFiles=myFlywayConfig.conf