package com.api.astrarest.service;

import java.util.List;

import com.api.astrarest.model.Salaries;

public interface SalariesService {

	List<Salaries> findByEmpNo(Integer empNo);

	Salaries findLatestByEmpNo(Integer empNo);

	void addSalary(Salaries salaries);

	void deleteByEmpNo(Integer empNo);

	void updateSalary(Integer empNo, Salaries salaries);

}
