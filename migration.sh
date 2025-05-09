mvn flyway:migrate \
    -Dflyway.url=${SPRING_DATASOURCE_URL} \
    -Dflyway.user=${SPRING_DATASOURCE_USERNAME} \
    -Dflyway.password=${SPRING_DATASOURCE_PASSWORD} \
    -Dflyway.schemas=${SPRING_DB_NAME} \
    -Dflyway.locations=classpath:db/migration \
    -Dflyway.baselineOnMigrate=true \
    -Dflyway.table=flyway_schema_history \
    -Dflyway.validateOnMigrate=true