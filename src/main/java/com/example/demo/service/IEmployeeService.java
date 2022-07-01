package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Employee;


public interface IEmployeeService {
Integer saveEmployee(Employee emp);
	
	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployee(Integer id);
	
	public void deleteEmployee(Integer id);
	
	public void updateEmployee(Employee emp,Integer id);
}
