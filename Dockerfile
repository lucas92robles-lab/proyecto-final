# Etapa 1: Construcción
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Despliegue
FROM quay.io/wildfly/wildfly:35.0.1.Final-jdk17
COPY --from=builder --chown=jboss:root /app/src/main/wildfly/ /opt/jboss/wildfly/
COPY --from=builder --chown=jboss:root /app/target/*.war /opt/jboss/wildfly/standalone/deployments/tactica01.war
EXPOSE 8080
CMD ["sh", "-c", "env | grep -E '^DB_(URL|USER)=' ; /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT:-8080}"]