package com.api.astrarest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.Salaries;
import com.api.astrarest.service.SalariesService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@Scope("session")
@RequestMapping("/salaries")
public class SalariesController {

	@Autowired
	private SalariesService salariesService;

	@JsonView(ViewFilter.Minimum.class)
	@GetMapping("/findByEmpNo/{empNo}")
	public List<Salaries> findByEmpNo(@PathVariable Integer empNo) {
		return salariesService.findByEmpNo(empNo);
	}

	@JsonView(ViewFilter.Minimum.class)
	@GetMapping("/findLatestByEmpNo/{empNo}")
	public Salaries findLatestByEmpNo(@PathVariable Integer empNo) {
		return salariesService.findLatestByEmpNo(empNo);
	}

	@PostMapping("/add")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void addSalaries(@RequestBody String body) {
		
		try {
			Salaries salaries = (Salaries) ObjectManipulation.instantiateClassFromName(Salaries.class.getName(), body);
			salariesService.addSalary(salaries);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@PutMapping("/update/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void updateSalaries( @PathVariable Integer empNo, @RequestBody String body) {
		
		try {
			Salaries salaries = (Salaries) ObjectManipulation.instantiateClassFromName(Salaries.class.getName(), body);
			salariesService.updateSalary(empNo, salaries);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@DeleteMapping("/delete/{empNo}")
	public void deleteByEmpNo(@PathVariable Integer empNo) {
		try {
			salariesService.deleteByEmpNo(empNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
		
}
