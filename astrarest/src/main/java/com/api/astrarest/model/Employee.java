package com.api.astrarest.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

import com.api.astrarest.generic.Enums.Gender;
import com.api.astrarest.generic.ViewFilter;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="employees")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "emp_no", discriminatorType = DiscriminatorType.INTEGER)
public class Employee {

	@JsonView(ViewFilter.Minimum.class)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="seq")
	@GenericGenerator(name = "seq", strategy="increment")
	@Column(name="emp_no")
	private Integer empNo;

	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	@JsonView(ViewFilter.Minimum.class)
	private Date birthDate;
	
	@Column(name="first_name")
	@JsonView(ViewFilter.Minimum.class)
	private String firstName;

	@Column(name="last_name")
	@JsonView(ViewFilter.Minimum.class)
	private String lastName;
	
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	@JsonView(ViewFilter.Minimum.class)
	private Gender gender;

	@Temporal(TemporalType.DATE)
	@Column(name="hire_date")
	@JsonView(ViewFilter.Minimum.class)
	private Date hireDate;
	
	public Integer getEmpNo() {
		return empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}
	
}
