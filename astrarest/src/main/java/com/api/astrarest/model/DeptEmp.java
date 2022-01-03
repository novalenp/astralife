package com.api.astrarest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="dept_emp")
@IdClass(DeptKey.class)
public class DeptEmp extends DateRange {

	@Id
	@Column(name="emp_no")
	private Integer empNo;
	
	@Id
	@ManyToOne
    @JoinColumn(name = "dept_no", referencedColumnName = "dept_no")
	@JsonView(ViewFilter.Minimalist.class)
	private Departments departments;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Departments getDepartments() {
		return departments;
	}

	public void setDepartments(Departments departments) {
		this.departments = departments;
	}

	
}
