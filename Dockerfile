# Etapa 1: Construcción (Render compila el proyecto)
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Despliegue (Render levanta WildFly y coloca tu app)
FROM quay.io/wildfly/wildfly:latest
COPY --from=builder /app/target/*.war /opt/jboss/wildfly/standalone/deployments/tactica01.war
EXPOSE 8080
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]