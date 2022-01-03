package com.api.astrarest.dto;

import javax.persistence.Embeddable;

import com.api.astrarest.generic.ViewFilter;
import com.api.astrarest.model.DateRange;
import com.fasterxml.jackson.annotation.JsonView;

@Embeddable
public class DepartmentsDTO extends DateRange {

	@JsonView(ViewFilter.Minimum.class)
	private String deptNo;
	
	@JsonView(ViewFilter.Minimum.class)
	private String deptName;
	
	@JsonView(ViewFilter.Minimum.class)
	private Integer managerEmpNo;
	
	@JsonView(ViewFilter.Minimum.class)
	private String managerName;
	
	public Integer getManagerEmpNo() {
		return managerEmpNo;
	}
	public void setManagerEmpNo(Integer managerEmpNo) {
		this.managerEmpNo = managerEmpNo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
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
