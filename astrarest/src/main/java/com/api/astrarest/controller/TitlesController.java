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
import com.api.astrarest.model.Titles;
import com.api.astrarest.service.TitlesService;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@Scope("session")
@RequestMapping("/titles")
public class TitlesController {

	@Autowired
	private TitlesService titlesService;

	@JsonView(ViewFilter.Minimum.class)
	@GetMapping("/findByEmpNo/{empNo}")
	public List<Titles> findByEmpNo(@PathVariable Integer empNo) {
		return titlesService.findByEmpNo(empNo);
	}

	@JsonView(ViewFilter.Minimum.class)
	@GetMapping("/findLatestByEmpNo/{empNo}")
	public Titles findLatestByEmpNo(@PathVariable Integer empNo) {
		return titlesService.findLatestByEmpNo(empNo);
	}

	@PostMapping("/add")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void addTitles(@RequestBody String body) {
		
		try {
			Titles titles = (Titles) ObjectManipulation.instantiateClassFromName(Titles.class.getName(), body);
			titlesService.addTitle(titles);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	@PutMapping("/update/{empNo}")
	@JsonView(ViewFilter.MediumSecondary.class)
	public void updateTitles( @PathVariable Integer empNo, @RequestBody String body) {
		
		try {
			Titles titles = (Titles) ObjectManipulation.instantiateClassFromName(Titles.class.getName(), body);
			titlesService.updateTitle(empNo, titles);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	@DeleteMapping("/delete/{empNo}")
	public void deleteByEmpNo(@PathVariable Integer empNo) {
		try {
			titlesService.deleteByEmpNo(empNo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
