package com.docker.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docker.demo.model.Employee;
import com.docker.demo.repository.EmployeeRepo;

@RestController
@RequestMapping("/employee")
public class EmployeeAPI {
	
	@Autowired
	EmployeeRepo employeeRepo;

	@PostMapping("/add")
	public ResponseEntity<String> add(@RequestBody Employee employee){
		Employee newEmployee = new Employee();
		newEmployee.setName(employee.getName());
		newEmployee.setEmail(employee.getEmail());
		newEmployee.setContact(employee.getContact());
		employeeRepo.save(newEmployee);
		return new ResponseEntity<String>("Added successfully with id "+newEmployee.getId(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getById(@PathVariable Long id){
		Optional<Employee> employee = employeeRepo.findById(id);
		if(employee.isPresent()) {
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("")
	public ResponseEntity<List<Employee>> getAll(){
		List<Employee> employee = (List<Employee>) employeeRepo.findAll();
		if(employee.isEmpty()) {
			return new ResponseEntity<List<Employee>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Employee>>(employee, HttpStatus.OK);
		
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Employee employee){
		Optional<Employee> employeeData = employeeRepo.findById(id);
		if(employeeData.isPresent()) {
			employeeData.get().setName(employee.getName());
			employeeData.get().setEmail(employee.getEmail());
			employeeData.get().setContact(employee.getContact());
			employeeRepo.save(employeeData.get());
			return new ResponseEntity<String>("Updated Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> update(@PathVariable Long id){
		Optional<Employee> employee = employeeRepo.findById(id);
		if(employee.isPresent()) {
			employeeRepo.delete(employee.get());
			return new ResponseEntity<String>("Deleted Successfully", HttpStatus.OK);
		}
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
