FROM quay.io/wildfly/wildfly:latest-jdk21
COPY target/tactica01.war /opt/jboss/wildfly/standalone/deployments/
    