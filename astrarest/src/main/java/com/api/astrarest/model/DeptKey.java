package com.api.astrarest.model;

import java.io.Serializable;

public class DeptKey implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5566120433572901827L;

	private Integer empNo;
	
	private String departments;
	
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}
	
}
