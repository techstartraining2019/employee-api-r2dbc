package com.ts.employee.rx.repository;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import com.ts.employee.rx.entities.Employee;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer> {

  @Query("SELECT * FROM employees WHERE email = $1")
  Flux<Employee> findByEmail(String email);

  // Mono<Void> deleteByEmpId(Long empId);

}
