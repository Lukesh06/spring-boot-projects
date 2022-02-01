package com.ekalavya.studentapi.student.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.studentapi.student.dto.StudentDetailsDto;
import com.ekalavya.studentapi.student.entity.StudentDetailsEntity;
import com.ekalavya.studentapi.student.repository.StudentRepository;

@Component
public class StudentDao {

	@Autowired
	private StudentRepository studentRepository;
	
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {
		StudentDetailsEntity studentDetailsEntity = new StudentDetailsEntity();
		BeanUtils.copyProperties(studentDetailsDto, studentDetailsEntity);
		studentDetailsEntity = studentRepository.save(studentDetailsEntity);
		BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
		return studentDetailsDto;
	}
 
	public List<StudentDetailsDto> getAllStudents(){
		List<StudentDetailsEntity> listStudentDetailsEntity = (List<StudentDetailsEntity>) studentRepository.findAll();
		
		return listStudentDetailsEntity.stream().map(studentDetailsEntity->
		{
			StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
			BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
			return studentDetailsDto;
		}).collect(Collectors.toList());
	}
	
	
}
