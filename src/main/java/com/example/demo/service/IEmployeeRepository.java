package com.example.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;

public interface IEmployeeRepository extends JpaRepository<Employee, Integer>{
	

}
