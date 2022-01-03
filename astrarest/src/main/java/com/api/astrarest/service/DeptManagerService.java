package com.api.astrarest.service;

import java.util.List;

import com.api.astrarest.model.DeptManager;

public interface DeptManagerService {

	List<DeptManager> findByDeptNo(String deptNo);

	DeptManager findLatestByDeptNo(String deptNo);

	void deleteByEmpNo(Integer empNo);

}
