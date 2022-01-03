package com.api.astrarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.astrarest.model.DeptManager;

public interface DeptManagerRepository extends JpaRepository<DeptManager, String>{
	List<DeptManager> findByDeptNo(String deptNo);

	@Query(value="select d from DeptManager d WHERE d.fromDate = (select max(d2.fromDate) from DeptManager d2 where d2.deptNo=?1) and d.deptNo =?1")
	DeptManager findLatestByDeptNo(String deptNo);

	void deleteByEmployeeEmpNo(Integer empNo);
}
