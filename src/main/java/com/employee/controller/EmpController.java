package com.employee.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

@Controller
public class EmpController {

	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public String  home(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees());
		return "home";
	}
	
	@GetMapping("/addemp")
	public String addEmp(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee",employee);
		return "add_emp";
	}
	
	@PostMapping("/saveEmp")
	public String saveEmp(@ModelAttribute("employee") Employee employee) {
		employeeService.createEmployee(employee);
		return "redirect:/";
	}
	
	/*
	 * echo "# github-actions-test" >> README.md git init git add README.md git
	 * commit -m "first commit" git branch -M main git remote add origin
	 * https://github.com/Harshalss21/github-actions-test.git git push -u origin
	 * main
	 */
	
	
}
