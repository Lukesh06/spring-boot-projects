package com.ekalavya.result.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.result.dto.ResultDto;
import com.ekalavya.result.entity.ResultEntity;
import com.ekalavya.result.repository.ResultRepository;

@Component
public class ResultDao {

	@Autowired
	public ResultRepository resultRepository;
	
	public ResultDto saveResult(ResultDto requestDto) {
		ResultEntity resultEntity = new ResultEntity();
		BeanUtils.copyProperties(requestDto, resultEntity);
		resultEntity = resultRepository.save(resultEntity);
		BeanUtils.copyProperties(resultEntity, requestDto);
		return requestDto;
	}
	
	public List<ResultDto> findResultByStudentId(int studentId){
		List<ResultEntity> listResultEntity = resultRepository.findByStudentId(studentId);
		return prepareListOfDto(listResultEntity);
	}
	
	private List<ResultDto> prepareListOfDto(List<ResultEntity> listResultEntity) {
		return listResultEntity.stream().map(resultEntity -> {
			ResultDto resultDto = new ResultDto();
			BeanUtils.copyProperties(resultEntity, resultDto);
			return resultDto;
		}).collect(Collectors.toList());
	}
}
