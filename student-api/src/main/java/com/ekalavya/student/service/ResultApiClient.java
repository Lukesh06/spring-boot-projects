package com.ekalavya.student.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ekalavya.student.dto.ResultDto;

@FeignClient(name = "student-result")
public interface ResultApiClient {

	@GetMapping("/result/student/{id}")
	public ResponseEntity<List<ResultDto>> searchByStudentId(@PathVariable int id);
}
