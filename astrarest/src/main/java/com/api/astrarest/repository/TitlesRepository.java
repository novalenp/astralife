package com.api.astrarest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.api.astrarest.model.Titles;

public interface TitlesRepository extends JpaRepository<Titles, Integer>{
	List<Titles> findByEmpNo(Integer empNo);

	@Query(value="select t from Titles t where t.fromDate = (select max(t2.fromDate) from Titles t2 where t2.empNo =?1) and t.empNo =?1")
	Titles findLatestByEmpNo(Integer empNo);
	
	void deleteByEmpNo(Integer empNo); 
}
