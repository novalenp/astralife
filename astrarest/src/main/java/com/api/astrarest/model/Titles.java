package com.api.astrarest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="titles")
@IdClass(TitlesKey.class)
public class Titles extends DateRange  {
	
	@Id
	@Column(name="emp_no")
	private Integer empNo;
	
	@Column(name="title")
	@JsonView(ViewFilter.Minimum.class)
	private String title;

	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
}
