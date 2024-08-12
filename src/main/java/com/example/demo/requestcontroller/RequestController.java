package com.example.demo.requestcontroller;

import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;
import com.example.demo.mappers.ResponseControllerMapper;

@RestController
public class RequestController {
	

	@CrossOrigin
	@GetMapping("/getResponse")
	public ResponseEntity<Object> getResponse(@RequestBody Map<String, Object> reqBody){
		
		
		System.out.println(reqBody.toString());
		Map<String,Object>  resBody = ResponseControllerMapper.successResponseMap();
		resBody.put("get", reqBody.get("Client"));
		return ResponseEntity.status(200).body(resBody);
	}
	@CrossOrigin
	@PostMapping("/createPost")
	public ResponseEntity<Object> postResponse(@RequestBody Map<String,Object>reqBody){
		Map<String,Object>  resBody= ResponseControllerMapper.successResponseMap();
		resBody.put("post", reqBody.get("data"));
		return ResponseEntity.status(200).body(resBody);
	}
	@CrossOrigin
	@PatchMapping("/createPatch")
	public ResponseEntity<Object> patchResponse(@RequestBody Map<String,Object>reqBody){
		Map<String,Object>  resBody= ResponseControllerMapper.successResponseMap();
		resBody.put("patch", reqBody.get("data"));
		return ResponseEntity.status(200).body(resBody);
	}
	@CrossOrigin
	@PutMapping("/createPut")
	public ResponseEntity<Object> putResponse(@RequestBody Map<String,Object>reqBody){
		Map<String,Object>  resBody= ResponseControllerMapper.successResponseMap();
		resBody.put("put", reqBody.get("data"));
		return ResponseEntity.status(200).body(resBody);
	}
	@CrossOrigin
	@DeleteExchange("/delete")
	public ResponseEntity<Object> deleteResponse(@RequestBody Map<String,Object>reqBody){
		Map<String,Object>  resBody= ResponseControllerMapper.successResponseMap();
		resBody.put("delete", reqBody.get("data"));
		return ResponseEntity.status(200).body(resBody);
	}
}
