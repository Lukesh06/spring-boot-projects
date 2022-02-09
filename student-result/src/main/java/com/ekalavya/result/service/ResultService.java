package com.ekalavya.result.service;

import java.util.List;

import com.ekalavya.result.dto.ResultDto;

public interface ResultService {

	public ResultDto saveResult(ResultDto requestDto);
	
	public List<ResultDto> findResultByStudentId(int studentId);
}
