package com.ts.employee.rx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@EnableR2dbcRepositories
public class EmployeeApiR2dbcApplication {

  public static void main(String[] args) {
    SpringApplication.run(EmployeeApiR2dbcApplication.class, args);
  }

}
