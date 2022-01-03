package com.api.astrarest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.model.Titles;
import com.api.astrarest.repository.TitlesRepository;
import com.api.astrarest.service.TitlesService;

@Service
public class TitlesServiceImpl implements TitlesService{

	private static final Logger logger = LoggerFactory.getLogger(TitlesServiceImpl.class);

	@Autowired
	private TitlesRepository titlesRepository;
	
	@Override
	public List<Titles> findByEmpNo(Integer empNo) {
		logger.info("TitlesServiceImpl findByEmpNo() start | empNo : {}", empNo);	
		return titlesRepository.findByEmpNo(empNo);

	}
	
	@Override
	public Titles findLatestByEmpNo(Integer empNo) {
		logger.info("TitlesServiceImpl findLatestByEmpNo() start | empNo : {}", empNo);	
		return titlesRepository.findLatestByEmpNo(empNo);
	}
	
	@Transactional
	@Override
	public void addTitle(Titles titles) {
		logger.info("TitlesServiceImpl addTitle() start : " , titles.getTitle());		
		titlesRepository.save(titles);
	}
	
	@Transactional
	@Override
	public void updateTitle(Integer empNo, Titles titles) {
		
		try {
			logger.info("TitlesServiceImpl updateTitle() start");
			Titles origin = (Titles) ObjectManipulation.evaluateOptional(titlesRepository.findLatestByEmpNo(empNo));
			origin.setToDate(new Date());
			titlesRepository.save(origin);

			Titles t = new Titles();
			t.setFromDate(new Date());
			t.setTitle(titles.getTitle());
			t.setEmpNo(empNo);
			titlesRepository.save(t);
		} catch (Exception e) {
			e.printStackTrace();			
		}
	}
	
	@Override
	@Transactional
	public void deleteByEmpNo(Integer empNo) {
		titlesRepository.deleteByEmpNo(empNo);
	}

}
