package io.undefvar.springbatchdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("employees")
public class Employee {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String department;
    private String gender;
    private String jobTitle;
    private Integer yearsOfExperience;
    private Double salary;
}
