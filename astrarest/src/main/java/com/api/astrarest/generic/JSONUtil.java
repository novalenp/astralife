package com.api.astrarest.generic;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtil {
	private static final ObjectMapper modelMapper = new ObjectMapper();
	
	public static Object instantiateObjectFromJson(String data, String clazzName) throws JsonParseException, JsonMappingException, ClassNotFoundException, IOException {
		return modelMapper.readValue(data, Class.forName(clazzName));
	}
}
