package com.api.astrarest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.api.astrarest.dto.EmployeeDTO;
import com.api.astrarest.dto.EmployeeHistoryDTO;
import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.Employee;
import com.api.astrarest.service.EmployeeService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@Scope("session")
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/basic/all")
	@JsonView(ViewFilter.Minimum.class)
	public List<Employee> findAll() {
		return employeeService.findAll();
	}
	
	@GetMapping("/basic/{empNo}")
	public Employee findByEmpNo(@PathVariable Integer empNo) {
		return employeeService.findByEmpNo(empNo);
	}
	
	@GetMapping("/current/all")
	@JsonView(ViewFilter.MediumSecondary.class)
	public List<EmployeeDTO> findCurrentAll() throws JsonProcessingException {
		return employeeService.findCurrentAll();
	}
	
	@GetMapping("/current/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public EmployeeDTO findCurrentByEmpNoDto(@PathVariable Integer empNo) throws JsonProcessingException {
		return employeeService.findCurrentByEmpNoDto(empNo);
	}

	@GetMapping("/history/all")
	@JsonView(ViewFilter.MediumSecondary.class)
	public List<EmployeeHistoryDTO> findHistoryAll() throws JsonProcessingException {
		return employeeService.findHistoryAll();
	}
	
	@GetMapping("/history/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public EmployeeHistoryDTO findHistoryByEmpNoDto(@PathVariable Integer empNo) throws JsonProcessingException {
		return employeeService.findHistoryByEmpNoDto(empNo);
	}
			
	@PostMapping("/add")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void addEmployee(@RequestBody String body) {
		logger.info("EmployeeController | addEmployee starts...");
		
		try {
			EmployeeDTO dto = (EmployeeDTO) ObjectManipulation.instantiateClassFromName(EmployeeDTO.class.getName(), body);
			employeeService.addEmployee(dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@PutMapping("/update/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void updateEmployee( @PathVariable Integer empNo, @RequestBody String body) {
		logger.info("EmployeeController | updateEmployee , empNo : {}", empNo);
		
		try {
			EmployeeDTO dto = (EmployeeDTO) ObjectManipulation.instantiateClassFromName(EmployeeDTO.class.getName(), body);
			employeeService.updateEmployee(empNo, dto);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@DeleteMapping("/delete/{empNo}")
	public void deleteByEmpNo(@PathVariable Integer empNo) {
		try {
			employeeService.deleteById(empNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
