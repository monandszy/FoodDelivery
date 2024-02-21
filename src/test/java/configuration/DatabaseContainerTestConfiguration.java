package configuration;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
public class DatabaseContainerTestConfiguration {
   public static final String USERNAME = "test";
   public static final String PASSWORD = "test";
   public static final String CONTAINER = "postgres:16.1";

   @Bean
   @SuppressWarnings("resource")
   PostgreSQLContainer<?> postgresqlContainer() {
      PostgreSQLContainer<?> postgresqlContainer = new PostgreSQLContainer<>(CONTAINER)
          .withUsername(USERNAME)
          .withPassword(PASSWORD);
      postgresqlContainer.start();
      return postgresqlContainer;
   }

   @Bean
   DataSource dataSource(final PostgreSQLContainer<?> postgresqlContainer) {
      return DataSourceBuilder.create()
          .type(HikariDataSource.class)
          .driverClassName(postgresqlContainer.getDriverClassName())
          .url(postgresqlContainer.getJdbcUrl())
          .username(postgresqlContainer.getUsername())
          .password(postgresqlContainer.getPassword())
          .build();
   }

   @Bean
   public ServletWebServerFactory servletWebServerFactory() {
      return new TomcatServletWebServerFactory();
   }
}