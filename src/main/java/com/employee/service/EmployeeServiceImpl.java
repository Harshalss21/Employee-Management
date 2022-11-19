package com.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	@Override
    public String deleteEmp(long id) {
        employeeRepository.deleteById(id);
        return "deleted";
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize,String sortField,String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending(); 
        Pageable page = PageRequest.of(pageNo-1, pageSize, sort);
        return employeeRepository.findAll(page);
    }

	
}
