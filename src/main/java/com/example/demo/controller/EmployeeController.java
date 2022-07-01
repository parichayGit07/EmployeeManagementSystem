package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {
@Autowired
EmployeeServiceImpl service;

@CrossOrigin
@PostMapping("/create")
public ResponseEntity<Integer> create(@RequestBody Employee emp){
	Integer id = 0;
	try {
		id = service.saveEmployee(emp);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(id,HttpStatus.OK);
}

@CrossOrigin
@GetMapping("/getAllEmployees")
public ResponseEntity<List<Employee>> getAll(){
	List<Employee> empList = null;
	try {
		empList = service.getAllEmployees();
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(empList,HttpStatus.OK);
}

@GetMapping("/employee/{id}")
public ResponseEntity<Optional<Employee>> getById(@PathVariable Integer id){
	Optional<Employee> emp = null;
	try {
		emp = service.getEmployee(id);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	if(emp.isEmpty()) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	}
	return new ResponseEntity<>(emp,HttpStatus.OK);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<Employee> delete(@PathVariable Integer id){
	try {
		service.deleteEmployee(id);
	}catch(EmptyResultDataAccessException empty) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(HttpStatus.OK);
}

@PutMapping("/update/{id}")
public ResponseEntity<Employee> update(@PathVariable Integer id, @RequestBody Employee emp){
	try {
		service.updateEmployee(emp,id);
	}catch(NoSuchElementException noSuchElement) {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	catch(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	return new ResponseEntity<>(HttpStatus.OK);
}

}
