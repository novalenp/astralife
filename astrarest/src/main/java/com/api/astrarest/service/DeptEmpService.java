package com.api.astrarest.service;

import java.util.List;

import com.api.astrarest.dto.DepartmentsDTO;
import com.api.astrarest.model.DeptEmp;

public interface DeptEmpService {

	List<DeptEmp> findByEmpNo(Integer empNo);

	DeptEmp findLatestByEmpNo(Integer empNo);

	void addDeptEmp(DeptEmp deptEmp);

	void deleteByEmpNo(Integer empNo);

	void updateDeptEmp(Integer empNo, DeptEmp deptEmp);

	DepartmentsDTO findEmployeeDepartmentDetails(Integer empNo);

	List<DepartmentsDTO> findEmployeeDepartmentHistory(Integer empNo);


}
