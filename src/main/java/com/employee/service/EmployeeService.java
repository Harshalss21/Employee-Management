package com.employee.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.employee.model.Employee;

public interface EmployeeService {
	
	public List<Employee> getAllEmployees();
	public Employee createEmployee(Employee employee);
	public Employee getEmpById(long id);
	public String deleteEmp(long id);
	public Page<Employee> findPaginated(int pageNo,int pageSize,String sortField,String sortDir);

}
