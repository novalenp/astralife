package com.api.astrarest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.DeptManager;
import com.api.astrarest.service.DeptManagerService;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@Scope("session")
@RequestMapping("/deptmanager")
public class DeptManagerController {

	@Autowired
	private DeptManagerService deptManagerService;

	@JsonView(ViewFilter.MediumSecondary.class)
	@GetMapping("/findByDeptNo/{deptNo}")
	public List<DeptManager> findByDeptNo(@PathVariable String deptNo) throws JsonMappingException, JsonProcessingException {
		return deptManagerService.findByDeptNo(deptNo);
		
	}

	@JsonView(ViewFilter.MediumSecondary.class)
	@GetMapping("/findLatestByDeptNo/{deptNo}")
	public DeptManager findLatestByDeptNo(@PathVariable String deptNo) {
		return deptManagerService.findLatestByDeptNo(deptNo);
	}
	
}
