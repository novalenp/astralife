package com.api.astrarest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.astrarest.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
