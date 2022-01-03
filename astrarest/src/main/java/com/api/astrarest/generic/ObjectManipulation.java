package com.api.astrarest.generic;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.api.astrarest.dto.EmployeeDTO;
import com.api.astrarest.model.Employee;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ObjectManipulation {
	public static Object instantiateClassFromName(String clazzName, String data) throws InstantiationException, IllegalAccessException, ClassNotFoundException, JsonParseException, JsonMappingException, IOException {
		
		if(null==data && StringUtils.isEmpty(data)){
			return Class.forName(clazzName).newInstance();
		} else {
			return JSONUtil.instantiateObjectFromJson(data, clazzName);
		}
	}
	
	public static Object evaluateOptional(Object obj) {
		if (obj instanceof java.util.Optional) {
			return ((Optional<?>) obj).orElse(null);
		} else {
			return obj;
		}
	}
	
	public static String checkNotNull(String x) {
		if (x == null || x.isEmpty() || x.equalsIgnoreCase("") || x.equalsIgnoreCase("null")) {
			return "";
		}

		return x;
	}
	
	public static Employee employeeMapper(EmployeeDTO origin, Employee dest) {
		
		if(null != origin.getEmpNo()) {
			dest.setEmpNo(origin.getEmpNo());
		}
		if(null != origin.getBirthDate()) {
			dest.setBirthDate(origin.getBirthDate());
		}
		if(null != origin.getFirstName()) {
			dest.setFirstName(origin.getFirstName());
		}
		if(null != origin.getLastName()) {
			dest.setLastName(origin.getLastName());
		}
		if(null != origin.getHireDate()) {
			dest.setHireDate(origin.getHireDate());
		}
		if(null != origin.getGender()) {
			dest.setGender(origin.getGender());
		}
		
		
		return dest;
	}
	
}
