package com.api.astrarest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.api.astrarest.dto.DepartmentsDTO;
import com.api.astrarest.dto.EmployeeDTO;
import com.api.astrarest.dto.EmployeeHistoryDTO;
import com.api.astrarest.generic.ObjectManipulation;
import com.api.astrarest.model.Departments;
import com.api.astrarest.model.DeptEmp;
import com.api.astrarest.model.Employee;
import com.api.astrarest.model.Salaries;
import com.api.astrarest.model.Titles;
import com.api.astrarest.repository.EmployeeRepository;
import com.api.astrarest.service.DeptEmpService;
import com.api.astrarest.service.DeptManagerService;
import com.api.astrarest.service.EmployeeService;
import com.api.astrarest.service.SalariesService;
import com.api.astrarest.service.TitlesService;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private SalariesService salariesService;

	@Autowired
	private TitlesService titlesService;
	
	@Autowired
	private DeptEmpService deptEmpService;
	
	@Autowired
	private DeptManagerService deptManagerService;
	
	@Override
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public Employee findByEmpNo(Integer empNo) {
		return (Employee) ObjectManipulation.evaluateOptional(employeeRepository.findById(empNo));
	}
	
	@Override
	public List<EmployeeDTO> findCurrentAll() throws JsonProcessingException {
		List<EmployeeDTO> lists = new ArrayList<EmployeeDTO>();
		List<Employee> employees = findAll();
		
		
		for(Employee e : employees) {
			EmployeeDTO dto = buildEmployeeDTO(e);
			lists.add(dto);
		}
		
		return lists;
	}
	
	@Override
	public EmployeeDTO findCurrentByEmpNoDto(Integer empNo) throws JsonProcessingException {
		Employee e = findByEmpNo(empNo);
		return buildEmployeeDTO(e);
	}

	@Override
	public List<EmployeeHistoryDTO> findHistoryAll() throws JsonProcessingException {
		List<EmployeeHistoryDTO> lists = new ArrayList<EmployeeHistoryDTO>();
		List<Employee> employees = findAll();
		
		for(Employee e : employees) {
			EmployeeHistoryDTO dto = buildEmployeeHistoryDTO(e);
			lists.add(dto);
		}
		
		return lists;
	}
	
	
	@Override
	public EmployeeHistoryDTO findHistoryByEmpNoDto(Integer empNo) throws JsonProcessingException {		
		Employee e = findByEmpNo(empNo);
		return buildEmployeeHistoryDTO(e);
	}
	
	@Transactional
	@Override
	public void addEmployee(EmployeeDTO dto) {
		logger.info("EmployeeServiceImpl addEmployee() start");		
		
		
		try {
			Employee e = new Employee();
			logger.info("gender : {}", dto.getGender());
			logger.info("birthdate : {}", dto.getBirthDate());
			logger.info("firstname : {}", dto.getFirstName());
			logger.info("lastname : {}", dto.getLastName());
			logger.info("hiredate : {}", dto.getHireDate());
			
			e = ObjectManipulation.employeeMapper(dto, e);
			
			e = employeeRepository.save(e);

			logger.info("getEmpNo : {}", e.getEmpNo());	

			logger.info("getTitles : {}", dto.getTitles());	
			if(null != dto.getTitles()) {
				Titles t = dto.getTitles();
				t.setEmpNo(e.getEmpNo());
				titlesService.addTitle(t);
			}

			logger.info("getSalaries : {}", dto.getSalaries());	
			if(null != dto.getSalaries()) {
				Salaries s = dto.getSalaries();
				s.setEmpNo(e.getEmpNo());
				salariesService.addSalary(s);
			}
			
			logger.info("getDepartment : {}", dto.getDepartment());	
			if(null != dto.getDepartment()) {
				DeptEmp de = new DeptEmp();
				de.setEmpNo(e.getEmpNo());
				
				Departments dep = new Departments();
				if(null != dto.getDepartment().getDeptNo()) {
					dep.setDeptNo(dto.getDepartment().getDeptNo());
				}
				if(null != dto.getDepartment().getDeptName()) {
					dep.setDeptName(dto.getDepartment().getDeptName());
				}
				de.setDepartments(dep);
				
				if(null != dto.getDepartment().getFromDate()) {
					de.setFromDate(dto.getDepartment().getFromDate());
				}
				if(null != dto.getDepartment().getToDate()) {
					de.setToDate(dto.getDepartment().getToDate());
				}
				
				deptEmpService.addDeptEmp(de);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Transactional
	@Override
	public void updateEmployee(Integer empNo, EmployeeDTO dto) {
		logger.info("EmployeeServiceImpl updateEmployee() start");
		Employee e = (Employee) ObjectManipulation.evaluateOptional(employeeRepository.findById(empNo));
		
		e = ObjectManipulation.employeeMapper(dto, e);

		logger.info("ObjectManipulation finished ");	
		e = employeeRepository.save(e);
		
		logger.info("getTitles : {}", dto.getTitles());	
		if(null != dto.getTitles()) {
			Titles t = dto.getTitles();
			titlesService.updateTitle(e.getEmpNo(), t);
		}
		
		logger.info("getSalaries : {}", dto.getSalaries());	
		if(null != dto.getSalaries()) {
			Salaries s = dto.getSalaries();
			salariesService.updateSalary(e.getEmpNo(),s);
		}
		
		logger.info("getDepartment : {}", dto.getDepartment());	
		if(null != dto.getDepartment()) {
			DeptEmp de = new DeptEmp();
			
			Departments dep = new Departments();
			if(null != dto.getDepartment().getDeptNo()) {
				dep.setDeptNo(dto.getDepartment().getDeptNo());
			}
			if(null != dto.getDepartment().getDeptName()) {
				dep.setDeptName(dto.getDepartment().getDeptName());
			}
			
			de.setDepartments(dep);
			
			if(null != dto.getDepartment().getFromDate()) {
				de.setFromDate(dto.getDepartment().getFromDate());
			}
			if(null != dto.getDepartment().getToDate()) {
				de.setToDate(dto.getDepartment().getToDate());
			}
			
			deptEmpService.updateDeptEmp(e.getEmpNo(), de);
			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteById(Integer empNo) {
		
		salariesService.deleteByEmpNo(empNo);
		titlesService.deleteByEmpNo(empNo);
		deptEmpService.deleteByEmpNo(empNo);
		deptManagerService.deleteByEmpNo(empNo);
		
		employeeRepository.deleteById(empNo);
	}
	
	private EmployeeDTO buildEmployeeDTO(Employee employee) throws JsonProcessingException {
		
		EmployeeDTO dto = new EmployeeDTO();
		Titles title = titlesService.findLatestByEmpNo(employee.getEmpNo());
		Salaries salary = salariesService.findLatestByEmpNo(employee.getEmpNo());
		DepartmentsDTO deptDto = deptEmpService.findEmployeeDepartmentDetails(employee.getEmpNo());
		
		dto.setBirthDate(employee.getBirthDate());
		dto.setEmpNo(employee.getEmpNo());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setGender(employee.getGender());
		dto.setHireDate(employee.getHireDate());
		dto.setTitles(title);
		dto.setSalaries(salary);
		dto.setDepartment(deptDto);
		
		return dto;
	}
	
	private EmployeeHistoryDTO buildEmployeeHistoryDTO(Employee employee) throws JsonProcessingException {
		
		EmployeeHistoryDTO dto = new EmployeeHistoryDTO();
		List<Titles> historyTitles = titlesService.findByEmpNo(employee.getEmpNo());
		List<Salaries> historySalaries = salariesService.findByEmpNo(employee.getEmpNo());
		List<DepartmentsDTO> historyDepartment = deptEmpService.findEmployeeDepartmentHistory(employee.getEmpNo());
		
		dto.setBirthDate(employee.getBirthDate());
		dto.setEmpNo(employee.getEmpNo());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		dto.setGender(employee.getGender());
		dto.setHireDate(employee.getHireDate());
		dto.setHistoryTitles(historyTitles);
		dto.setHistorySalaries(historySalaries);
		dto.setHistoryDepartment(historyDepartment);
		
		return dto;
	}
}
