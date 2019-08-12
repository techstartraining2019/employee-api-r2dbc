package com.ts.employee.rx.config;

import static java.util.Arrays.asList;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import com.ts.employee.rx.entities.Employee;
import com.ts.employee.rx.repository.EmployeeRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;

@Configuration
public class R2dbcPostgresqlConfiguration extends AbstractR2dbcConfiguration {


  @Value("${datasource.host}")
  private String host;

  @Value("${datasource.port}")
  private int port;

  @Value("${datasource.database}")
  private String database;

  @Value("${datasource.username}")
  private String username;

  @Value("${datasource.password}")
  private String password;


  @Bean
  @Override
  public PostgresqlConnectionFactory connectionFactory() {
    return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration.builder().host(host)
        .database(database).username(username).password(password).port(port).build());
  }


  @Bean
  public CommandLineRunner commandLineRunner(EmployeeRepository employeeRepository) {
    return args -> {
      DatabaseClient databaseClient = DatabaseClient.create(connectionFactory());
      asList("DROP TABLE IF EXISTS employees;",
          "CREATE TABLE employees(id serial primary key, empid bigint ,name varchar,salary bigint,depid bigint,email varchar);")
              .forEach(s -> databaseClient.execute().sql(s).fetch().rowsUpdated().block());
      employeeRepository.save(new Employee(null, 123l, "test", 20000, 23, "test@gmail.com")).log()
          .subscribe();
      employeeRepository.findAll().log().subscribe(System.out::println);
    };
  }


}


