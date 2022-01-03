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
@Table(name="dept_manager")
@IdClass(DeptManKey.class)
public class DeptManager extends DateRange {

	@Id
	@Column(name="dept_no")
	private String deptNo;

	@Id
	@JsonView(ViewFilter.Minimalist.class)
	@ManyToOne
    @JoinColumn(name = "emp_no", referencedColumnName = "emp_no")
	private Employee employee;

	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
