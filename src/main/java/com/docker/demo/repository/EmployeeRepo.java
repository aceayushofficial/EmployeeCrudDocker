package com.docker.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.docker.demo.model.Employee;

public interface EmployeeRepo extends CrudRepository<Employee, Long> {

}
