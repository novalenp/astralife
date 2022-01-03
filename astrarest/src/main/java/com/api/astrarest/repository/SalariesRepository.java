package com.api.astrarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.astrarest.model.Salaries;

public interface SalariesRepository extends JpaRepository<Salaries, Integer> {
	List<Salaries> findByEmpNo(Integer empNo);

	@Query(value="select s from Salaries s WHERE s.fromDate = (select max(s2.fromDate) from Salaries s2 where s2.empNo =?1) and s.empNo =?1")
	Salaries findLatestByEmpNo(Integer empNo);

	void deleteByEmpNo(Integer empNo);
}
