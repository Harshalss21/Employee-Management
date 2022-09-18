package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> empList = employeeRepository.findAll();
		return empList;
	}

	@Override
	public Employee createEmployee(Employee employee) {
		Employee emp = employeeRepository.save(employee);
		return emp;
	}

	@Override
	public Employee getEmpById(long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		Employee employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		}
		else {
			throw new RuntimeException("Employee with "+id+ " is not present");
		}
		return employee;
	}

	
}
