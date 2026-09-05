package org.lito.jakarta;

import jakarta.ws.rs.core.Application;
import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.ws.rs.ApplicationPath;

@DataSourceDefinition(
    name = "java:/jdbc/TacticaDS",
    className = "org.postgresql.ds.PGSimpleDataSource",
    url = "jdbc:postgresql://ep-wandering-surf-a50ns2i1.us-east-2.aws.neon.tech/neondb?sslmode=require",
    user = "neondb_owner",
    password = "npg_VJX5e7WDtKcd"
)
@ApplicationPath("/app")
public class InfoTactica extends Application {
    
}