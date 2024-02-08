package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ems.entity.Employee;
import com.ems.service.EmpService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmpController {
	
	@Autowired
	private EmpService service;
	
	@GetMapping("/")  //getting data model attribute from db
	public String home(Model m) {
		
		List<Employee> emp=service.getAllEmp();
		m.addAttribute("emp",emp); // give object to the table data in index.html with key value pair 
		
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addEmpForm() {
		return "add_emp";
	}
	
	@PostMapping("/register")  //to get user input data use @Model attribute
	public String empRegister(@ModelAttribute Employee e,HttpSession session) {
		
		System.out.println(e);
		
		service.addEmp(e);
		session.setAttribute("msg","Employee details added successfully...! ");
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")  //particular id madhun user cha data ghene
	public String edit(@PathVariable int id,Model m) 
	{
		
		Employee e=service.getEmpById(id);
		m.addAttribute("emp", e);
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session)
	{
		
		service.addEmp(e);
		session.setAttribute("msg", "Employee details updated successfully...!");
		
		return "redirect:/";
	}
	
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id,HttpSession session)
	{
		service.deleteEmp(id);
		session.setAttribute("msg", "Employee details is deleted successfully...!");
		return "redirect:/";
	}
	
	@GetMapping("/page/{pageno}")
	public String findPaginated(@PathVariable int pageno, Model m) {

		Page<Employee> emplist = service.getEMpByPaginate(pageno, 2);
		m.addAttribute("emp", emplist);
		m.addAttribute("currentPage", pageno);
		m.addAttribute("totalPages", emplist.getTotalPages());
		m.addAttribute("totalItem", emplist.getTotalElements());
		return "index";
	}

}
