package com.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/update/{id}")
	public String getEmpById(@PathVariable("id") long id,Model model) {
	    Employee employee = employeeService.getEmpById(id);
	    model.addAttribute(employee);
	    return "update";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
	    employeeService.deleteEmp(id);
	    return "redirect:/";
	}
	
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo,
	        @RequestParam("sortField") String sortField,
	        @RequestParam("sortDir") String sortDir,
	        Model model) {
	    Page<Employee> page = employeeService.findPaginated(pageNo, 5, sortField, sortDir);
	    List<Employee> listEmployees = page.getContent();
	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    
	    model.addAttribute("sortField",sortField);
	    model.addAttribute("sortDir", sortDir);
	    model.addAttribute("reverseDir", sortDir.equals("asc") ? "desc" : "asc");
	    
	    model.addAttribute("listEmployees", listEmployees);
	    return "home";
	}
	
	/*
	 * echo "# github-actions-test" >> README.md git init git add README.md git
	 * commit -m "first commit" git branch -M main git remote add origin
	 * https://github.com/Harshalss21/github-actions-test.git git push -u origin
	 * main
	 */
	
	
}
