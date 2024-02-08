package com.ems.service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ems.entity.Employee;
import com.ems.repository.EmpRepo;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;
	
	public void addEmp(Employee e) {
		repo.save(e);
		
	}
	
	public List<Employee> getAllEmp()
	{
		return repo.findAll();
	}
	
	
	public Employee getEmpById(int id)  // get specific users data
	{
		Optional<Employee> e=repo.findById(id); //controller la service dyachi (e object dene) 
		
		if(e.isPresent())
		{
			return e.get();
		}
		return null;
	}
	
	public void deleteEmp(int id)
	{
		repo.deleteById(id);
		
	}
	
	public Page<Employee> getEMpByPaginate(int currentPage, int size) {
		PageRequest p = PageRequest.of(currentPage, size);
		return repo.findAll(p);
	}
}
