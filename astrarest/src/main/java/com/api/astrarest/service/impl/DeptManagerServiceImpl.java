package com.api.astrarest.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.astrarest.model.DeptManager;
import com.api.astrarest.repository.DeptManagerRepository;
import com.api.astrarest.service.DeptManagerService;

@Service
public class DeptManagerServiceImpl implements DeptManagerService {
	private static final Logger logger = LoggerFactory.getLogger(DeptManagerServiceImpl.class);

	@Autowired
	private DeptManagerRepository deptManagerRepository;
	
	@Override
	public List<DeptManager> findByDeptNo(String deptNo) {
		logger.info("DeptManagerServiceImpl findByEmpNo() start | deptNo : {}", deptNo);	
		return deptManagerRepository.findByDeptNo(deptNo);
	}

	@Override
	public DeptManager findLatestByDeptNo(String deptNo) {
		logger.info("DeptManagerServiceImpl findLatestByEmpNo() start | deptNo : {}", deptNo);	
		return deptManagerRepository.findLatestByDeptNo(deptNo);
	}
	
	@Override
	public void deleteByEmpNo(Integer empNo) {
		logger.info("DeptManagerServiceImpl deleteByEmpNo() start | empNo : {}", empNo);	
		deptManagerRepository.deleteByEmployeeEmpNo(empNo);
	}
}
