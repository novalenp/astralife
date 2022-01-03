package com.api.astrarest.service;

import java.util.List;

import com.api.astrarest.dto.EmployeeDTO;
import com.api.astrarest.dto.EmployeeHistoryDTO;
import com.api.astrarest.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeService {

	List<Employee> findAll();

	Employee findByEmpNo(Integer empNo);

	void deleteById(Integer empNo);

	List<EmployeeDTO> findCurrentAll() throws JsonProcessingException;

	EmployeeDTO findCurrentByEmpNoDto(Integer empNo) throws JsonProcessingException;

	List<EmployeeHistoryDTO> findHistoryAll() throws JsonProcessingException;

	EmployeeHistoryDTO findHistoryByEmpNoDto(Integer empNo) throws JsonProcessingException;

	void addEmployee(EmployeeDTO dto);

	void updateEmployee(Integer empNo, EmployeeDTO dto);


}