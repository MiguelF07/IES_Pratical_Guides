package com.ies.ex3_2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ies.ex3_2.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}