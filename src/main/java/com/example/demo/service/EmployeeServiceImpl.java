package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
@Autowired
IEmployeeRepository repo;

@Override
public Integer saveEmployee(Employee emp) {
	// TODO Auto-generated method stub
	
	return repo.save(emp).getId();
}

@Override
public List<Employee> getAllEmployees() {
	// TODO Auto-generated method stub
	return repo.findAll();
}

@Override
public Optional<Employee> getEmployee(Integer id) {
	// TODO Auto-generated method stub
	return repo.findById(id);
}

@Override
public void deleteEmployee(Integer id) {
	// TODO Auto-generated method stub
	repo.deleteById(id);
}

@Override
public void updateEmployee(Employee emp, Integer id) {
	// TODO Auto-generated method stub
	Optional<Employee> e = repo.findById(id);
		e.get().setName(emp.getName());
		e.get().setDesignation(emp.getDesignation());
		e.get().setAge(emp.getAge());
		e.get().setEmail(emp.getEmail());
		e.get().setGender(emp.getGender());
		repo.save(e.get());
}

}
