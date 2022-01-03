package com.api.astrarest.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.model.Salaries;
import com.api.astrarest.repository.SalariesRepository;
import com.api.astrarest.service.SalariesService;

@Service
public class SalariesServiceImpl implements SalariesService {

	private static final Logger logger = LoggerFactory.getLogger(SalariesServiceImpl.class);

	@Autowired
	private SalariesRepository salariesRepository;
	
	@Override
	public List<Salaries> findByEmpNo(Integer empNo) {
		logger.info("SalariesServiceImpl findByEmpNo() start | empNo : {}", empNo);	
		return salariesRepository.findByEmpNo(empNo);
	}

	@Override
	public Salaries findLatestByEmpNo(Integer empNo) {
		logger.info("SalariesServiceImpl findLatestByEmpNo() start | empNo : {}", empNo);	
		return salariesRepository.findLatestByEmpNo(empNo);
	}

	@Transactional
	@Override
	public void addSalary(Salaries salaries) {
		logger.info("SalariesServiceImpl addSalary() start ");		
		salariesRepository.save(salaries);
	}

	@Transactional
	@Override
	public void updateSalary(Integer empNo, Salaries salaries) {
		
		try {
			logger.info("SalariesServiceImpl updateSalary() start");
			Salaries origin = (Salaries) ObjectManipulation.evaluateOptional(salariesRepository.findLatestByEmpNo(empNo));
			origin.setToDate(new Date());
			salariesRepository.save(origin);

			Salaries s = new Salaries();
			s.setFromDate(new Date());
			s.setSalary(salaries.getSalary());
			s.setEmpNo(empNo);
			salariesRepository.save(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByEmpNo(Integer empNo) {
		salariesRepository.deleteByEmpNo(empNo);
	}
}
