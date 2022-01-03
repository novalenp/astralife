package com.api.astrarest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="departments")
public class Departments {
	
	@Id
	@JsonView(ViewFilter.Minimum.class)
	@Column(name="dept_no")
	private String deptNo;

	@JsonView(ViewFilter.Minimum.class)
	@Column(name="dept_name")
	private String deptName;
	
	public String getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

}
