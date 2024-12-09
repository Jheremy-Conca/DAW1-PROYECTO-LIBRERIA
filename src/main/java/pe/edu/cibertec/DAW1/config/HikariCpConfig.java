package pe.edu.cibertec.DAW1.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HikariCpConfig {

    @Value("${DB_PROYECTO_URL}")
    private String dbProyectoUrl;
    @Value("${DB_PROYECTO_USER}")
    private String dbProyectoUser;
    @Value("${DB_PROYECTO_PASS}")
    private String dbProyectoPass;
    @Value("${DB_PROYECTO_DRIVER}")
    private String dbProyectoDriver;

    @Bean
    public HikariDataSource hikariDataSource() {

        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(dbProyectoUrl);
        config.setUsername(dbProyectoUser);
        config.setPassword(dbProyectoPass);
        config.setDriverClassName(dbProyectoDriver);

        config.setMaximumPoolSize(20);
        config.setMinimumIdle(5);
        config.setIdleTimeout(300000);
        config.setConnectionTimeout(30000);

        System.out.println("###### HikariCP initialized ######");
        return new HikariDataSource(config);

    }


}
