package com.api.astrarest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="salaries")
@IdClass(SalariesKey.class)
public class Salaries extends DateRange {
	

	@Id
	@Column(name="emp_no")
	private Integer empNo;
	
	@Id
	@Column(name="salary")
	@JsonView(ViewFilter.Minimum.class)
	private Integer salary;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	
	
}
