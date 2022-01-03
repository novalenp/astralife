package com.api.astrarest.dto;

import javax.persistence.Embeddable;

import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.Employee;
import com.api.astrarest.model.Salaries;
import com.api.astrarest.model.Titles;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Embeddable
public class EmployeeDTO extends Employee {

	@JsonView(ViewFilter.Minimum.class)
    @JsonSerialize()
	private Salaries salaries;
	
	@JsonView(ViewFilter.Minimum.class)
	@JsonSerialize()
	private Titles titles;

	@JsonView(ViewFilter.Minimum.class)
	@JsonSerialize()
	DepartmentsDTO department;
	
	public Salaries getSalaries() {
		return salaries;
	}
	public void setSalaries(Salaries salaries) {
		this.salaries = salaries;
	}
	public Titles getTitles() {
		return titles;
	}
	public void setTitles(Titles titles) {
		this.titles = titles;
	}
	public DepartmentsDTO getDepartment() {
		return department;
	}
	public void setDepartment(DepartmentsDTO department) {
		this.department = department;
	}
	
	
}
