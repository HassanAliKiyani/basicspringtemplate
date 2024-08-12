package com.example.demo.mappers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import java.sql.Timestamp;
import java.time.Instant;

public class ResponseControllerMapper {
	
	public static Map<String,Object>successResponseMap(){
		Map<String,Object>resultMap=new HashMap<>();
		resultMap.put("Timestamp", Timestamp.from(Instant.now()));
		resultMap.put("Success", true);
		return resultMap;
	}
	public static Map<String,Object>errorResponseMap(){
		Map<String,Object>resultMap=new HashMap<>();
		resultMap.put("Timestamp", Timestamp.from(Instant.now()));
		resultMap.put("Success", false);
		return resultMap;
	}
	public static ResponseEntity<Object> errorResponse(int errorCode,String errorMessage){
		Map<String,Object>errorMap=ResponseControllerMapper.errorResponseMap();
		errorMap.put("Success", false);
		errorMap.put("errorCode",errorCode);
		errorMap.put("error", errorMessage);
		return ResponseEntity.status(400).body(errorMap);
	}

}
