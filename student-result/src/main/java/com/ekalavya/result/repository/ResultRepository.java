package com.ekalavya.result.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ekalavya.result.entity.ResultEntity;

public interface ResultRepository extends CrudRepository<ResultEntity, Integer>{

	public List<ResultEntity> findByStudentId(int studentId);
	
}
