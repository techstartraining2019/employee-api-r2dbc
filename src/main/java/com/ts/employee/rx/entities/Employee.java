package com.ts.employee.rx.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table("employees")
@Data
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class Employee {
  @Id
  private Integer id;
  private Long empid;
  private String name;
  private Integer salary;
  private Integer depid;
  private String email;


}


