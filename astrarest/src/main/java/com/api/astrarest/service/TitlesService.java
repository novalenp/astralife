package com.api.astrarest.service;

import java.util.List;

import com.api.astrarest.model.Titles;

public interface TitlesService {

	List<Titles> findByEmpNo(Integer empNo);

	Titles findLatestByEmpNo(Integer empNo);

	void addTitle(Titles titles);

	void updateTitle(Integer empNo, Titles titles);

	void deleteByEmpNo(Integer empNo);

}
