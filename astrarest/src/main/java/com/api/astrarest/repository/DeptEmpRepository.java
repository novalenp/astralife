package com.api.astrarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.astrarest.model.DeptEmp;

public interface DeptEmpRepository extends JpaRepository<DeptEmp, Integer>{

	List<DeptEmp> findByEmpNo(Integer empNo);

	@Query(value="select d from DeptEmp d WHERE d.fromDate = (select max(d2.fromDate) from DeptEmp d2 where d2.empNo =?1) and d.empNo =?1")
	DeptEmp findLatestByEmpNo(Integer empNo);

	void deleteByEmpNo(Integer empNo);
}
