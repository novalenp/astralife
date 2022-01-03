package com.api.astrarest.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.astrarest.dto.DepartmentsDTO;
import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.model.DeptEmp;
import com.api.astrarest.model.DeptManager;
import com.api.astrarest.repository.DeptEmpRepository;
import com.api.astrarest.service.DeptEmpService;
import com.api.astrarest.service.DeptManagerService;

@Service
public class DeptEmpServiceImpl implements DeptEmpService {

	private static final Logger logger = LoggerFactory.getLogger(DeptEmpServiceImpl.class);

	@Autowired
	private DeptEmpRepository deptEmpRepository;
	
	@Autowired
	private DeptManagerService deptManagerService;
	
//	@Autowired
//	private DeptManagerService deptManagerService;
	
	@Override
	public List<DeptEmp> findByEmpNo(Integer empNo) {
		logger.info("DeptEmpServiceImpl findByEmpNo() start | empNo : {}", empNo);	
		return deptEmpRepository.findByEmpNo(empNo);
	}

	@Override
	public DeptEmp findLatestByEmpNo(Integer empNo) {
		logger.info("DeptEmpServiceImpl findLatestByEmpNo() start | empNo : {}", empNo);	
		return deptEmpRepository.findLatestByEmpNo(empNo);
	}
	
	@Override
	public DepartmentsDTO findEmployeeDepartmentDetails(Integer empNo) {
		logger.info("DeptEmpServiceImpl findEmployeeDepartmentDetails() start | empNo : {}", empNo);	

		DeptEmp deptEmp = findLatestByEmpNo(empNo);
		DeptManager deptManager = deptManagerService.findLatestByDeptNo(deptEmp.getDepartments().getDeptNo());
		DepartmentsDTO dto = buildDepartmentDTO(deptEmp, deptManager);
		
		return dto;
	}

	@Override
	public List<DepartmentsDTO> findEmployeeDepartmentHistory(Integer empNo) {
		logger.info("DeptEmpServiceImpl findEmployeeDepartmentDetails() start | empNo : {}", empNo);	

		List<DepartmentsDTO> listDto = new ArrayList<DepartmentsDTO>();
		
		try {
					
			List<DeptEmp> listDeptEmp = findByEmpNo(empNo);
			
			for(DeptEmp deptEmp : listDeptEmp) {
			
				DeptManager deptManager = deptManagerService.findLatestByDeptNo(deptEmp.getDepartments().getDeptNo());
				DepartmentsDTO dto = buildDepartmentDTO(deptEmp, deptManager);
				
				listDto.add(dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDto;
	}
	
	@Transactional
	@Override
	public void addDeptEmp(DeptEmp deptEmp) {
		logger.info("DeptEmpServiceImpl addDeptEmp() start ");	
		logger.info("getEmpNo : {}",deptEmp.getEmpNo());		
		logger.info("getFromDate : {}",deptEmp.getFromDate());			
		logger.info("getToDate : {}",deptEmp.getToDate());			
		logger.info("getDeptNo : {}",deptEmp.getDepartments().getDeptNo());			
		logger.info("getDeptName : {}",deptEmp.getDepartments().getDeptName());								
		deptEmpRepository.save(deptEmp);
	}
	
	@Transactional
	@Override
	public void updateDeptEmp(Integer empNo, DeptEmp deptEmp) {
		
		try {
			logger.info("DeptEmpServiceImpl updateDeptEmp() start");
			DeptEmp origin = (DeptEmp) ObjectManipulation.evaluateOptional(deptEmpRepository.findLatestByEmpNo(empNo));
			origin.setToDate(new Date());
			deptEmpRepository.save(origin);

			DeptEmp d = new DeptEmp();
			d.setFromDate(new Date());
			d.setDepartments(deptEmp.getDepartments());
			d.setEmpNo(empNo);
			deptEmpRepository.save(d);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

//	@Transactional
//	@Override
//	public void updateDeptEmp(DepartmentsDTO department, Employee e) {
//		logger.info("DeptEmpServiceImpl updateDeptEmp() start");
//		
//		try {
//			DeptEmp origin = (DeptEmp) ObjectManipulation.evaluateOptional(deptEmpRepository.findLatestByEmpNo(e.getEmpNo()));
//			
//			Date today = new Date();
//			origin.setToDate(today);
//			deptEmpRepository.save(origin);
//
//			department.setFromDate(today);
//			DeptEmp newOne = ObjectManipulation.deptEmpMapper(department, e);
//			deptEmpRepository.save(newOne);
//		} catch (Exception exc) {
//			exc.printStackTrace();			
//		}
//	}
	

	@Override
	@Transactional
	public void deleteByEmpNo(Integer empNo) {
		deptEmpRepository.deleteByEmpNo(empNo);
		
	}

	private DepartmentsDTO buildDepartmentDTO(DeptEmp deptEmp, DeptManager deptManager) {
		DepartmentsDTO dto = new DepartmentsDTO();
		
		try {
			
			if( null != deptEmp.getFromDate() ) {
				dto.setFromDate(deptEmp.getFromDate());
			}
			if( null != deptEmp.getToDate() ) {
				dto.setToDate(deptEmp.getToDate());
			}
			
			String deptName = "";
			if(null != deptEmp && null != deptEmp.getDepartments() && null != deptEmp.getDepartments().getDeptName()) {
				deptName = ObjectManipulation.checkNotNull(deptEmp.getDepartments().getDeptName());
			}
			String deptNo = "";
			if(null != deptEmp && null != deptEmp.getDepartments() && null != deptEmp.getDepartments().getDeptNo()) {
				deptNo = ObjectManipulation.checkNotNull(deptEmp.getDepartments().getDeptNo());
			}
			String firstName = "";
			if(null != deptManager && null != deptManager.getEmployee() && null != deptManager.getEmployee().getFirstName()) {
				firstName = ObjectManipulation.checkNotNull(deptManager.getEmployee().getFirstName());
			}
			String lastName = "";
			if(null != deptManager && null != deptManager.getEmployee() && null != deptManager.getEmployee().getLastName()) {
				lastName = ObjectManipulation.checkNotNull(deptManager.getEmployee().getLastName());
			}
			
			if(null != deptManager && null != deptManager.getEmployee() && null != deptManager.getEmployee().getEmpNo()) {
				dto.setManagerEmpNo(deptManager.getEmployee().getEmpNo());
			}
			
			
			dto.setDeptName(deptName);
			dto.setDeptNo(deptNo);
			dto.setManagerName(firstName.concat(" ").concat(lastName) );

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return dto;
	}
}
