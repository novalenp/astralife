package com.api.astrarest.dto;

import java.util.List;

import javax.persistence.Embeddable;

import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.Employee;
import com.api.astrarest.model.Salaries;
import com.api.astrarest.model.Titles;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Embeddable
public class EmployeeHistoryDTO extends Employee {

	@JsonView(ViewFilter.Minimum.class)
	@JsonSerialize()
	private List<Salaries> historySalaries;
	
	@JsonView(ViewFilter.Minimum.class)
	@JsonSerialize()
	private List<Titles> historyTitles;

	@JsonView(ViewFilter.Minimum.class)
	@JsonSerialize()
	private List<DepartmentsDTO> historyDepartment;

	public List<Salaries> getHistorySalaries() {
		return historySalaries;
	}

	public void setHistorySalaries(List<Salaries> historySalaries) {
		this.historySalaries = historySalaries;
	}

	public List<Titles> getHistoryTitles() {
		return historyTitles;
	}

	public void setHistoryTitles(List<Titles> historyTitles) {
		this.historyTitles = historyTitles;
	}

	public List<DepartmentsDTO> getHistoryDepartment() {
		return historyDepartment;
	}

	public void setHistoryDepartment(List<DepartmentsDTO> historyDepartment) {
		this.historyDepartment = historyDepartment;
	}
	
}
