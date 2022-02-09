package com.ekalavya.result.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalavya.result.dao.ResultDao;
import com.ekalavya.result.dto.ResultDto;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultDao resultDao;
	
	@Override
	public ResultDto saveResult(ResultDto requestDto) {
		return resultDao.saveResult(requestDto);
	}

	@Override
	public List<ResultDto> findResultByStudentId(int studentId) {
		return resultDao.findResultByStudentId(studentId);
	}

}
