package org.lito.jakarta;

import jakarta.ws.rs.core.Application;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;

@DataSourceDefinition(
    name = "java:/jdbc/TacticaDS", // Esto DEBE coincidir con el <jta-data-source> de tu persistence.xml
    className = "org.postgresql.ds.PGSimpleDataSource",
    url = "jdbc:postgresql://localhost:5432/tactica_db", // Reemplazá "tactica_db" por el nombre de tu BD en pgAdmin
    user = "admin", // POSTGRES_USER del docker-compose.yml
    password = "adminpassword" // POSTGRES_PASSWORD del docker-compose.yml
)

@ApplicationPath("/app") // API REST
public class InfoTactica extends Application {
    
}
