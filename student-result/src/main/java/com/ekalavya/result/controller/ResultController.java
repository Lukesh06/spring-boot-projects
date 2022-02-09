package com.ekalavya.result.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.result.dto.ResultDto;
import com.ekalavya.result.service.ResultService;

@RestController
@RequestMapping("/result")
public class ResultController {

	@Autowired
	private ResultService resultService;
	
	@PostMapping
	public ResponseEntity<ResultDto> saveResult(@RequestBody ResultDto resultDto) {
		ResultDto resultDtoResponse = resultService.saveResult(resultDto);
		return new ResponseEntity<ResultDto>(resultDtoResponse,HttpStatus.OK);
	}
	
	@GetMapping("/student/{id}")
	public ResponseEntity<List<ResultDto>> searchByStudentId(@PathVariable int id){
		List<ResultDto> listResultDto = resultService.findResultByStudentId(id);
		return new ResponseEntity<List<ResultDto>>(listResultDto,HttpStatus.OK);
	}
}
