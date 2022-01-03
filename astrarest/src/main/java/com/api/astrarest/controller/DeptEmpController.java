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
import com.api.astrarest.model.DeptEmp;
import com.api.astrarest.service.DeptEmpService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@Scope("session")
@RequestMapping("/deptemp")
public class DeptEmpController {

	@Autowired
	private DeptEmpService deptEmpService;

	@JsonView(ViewFilter.MediumPrimary.class)
	@GetMapping("/findByEmpNo/{empNo}")
	public List<DeptEmp> findByEmpNo(@PathVariable Integer empNo) throws JsonMappingException, JsonProcessingException {
		return deptEmpService.findByEmpNo(empNo);
	}

	@JsonView(ViewFilter.MediumPrimary.class)
	@GetMapping("/findLatestByEmpNo/{empNo}")
	public DeptEmp findLatestByEmpNo(@PathVariable Integer empNo) {
		return deptEmpService.findLatestByEmpNo(empNo);
	}

	@DeleteMapping("/delete/{empNo}")
	public void deleteByEmpNo(@PathVariable Integer empNo) {
		try {
			deptEmpService.deleteByEmpNo(empNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	@PutMapping("/update/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void updateDeptEmp( @PathVariable Integer empNo, @RequestBody String body) {
		
		try {
			DeptEmp deptEmp = (DeptEmp) ObjectManipulation.instantiateClassFromName(DeptEmp.class.getName(), body);
			deptEmpService.updateDeptEmp(empNo, deptEmp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@PostMapping("/add")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void addEmployee(@RequestBody String body) {
	
	try {
		DeptEmp deptEmp = (DeptEmp) ObjectManipulation.instantiateClassFromName(DeptEmp.class.getName(), body);
		deptEmpService.addDeptEmp(deptEmp);
		
	} catch (Exception e) {
		e.printStackTrace();
	}
			
}

}
