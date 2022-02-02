package com.ekalavya.student.dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.student.dto.StudentDetailsDto;
import com.ekalavya.student.entity.StudentDetailsEntity;
import com.ekalavya.student.repository.StudentRepository;

/**
 * Dao class to perform DB operations
 * 
 * @author Lukesh Bhendaker
 *
 */
@Component
public class StudentDao {

	@Autowired
	private StudentRepository studentRepository;

	/**
	 * This method is used to save student records in DB
	 * 
	 * @param studentDetailsDto DTO bean object with student details to save
	 * @return studentDetailsDto DTO bean object after save
	 */
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {
		StudentDetailsEntity studentDetailsEntity = new StudentDetailsEntity();
		BeanUtils.copyProperties(studentDetailsDto, studentDetailsEntity);
		studentDetailsEntity = studentRepository.save(studentDetailsEntity);
		BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
		return studentDetailsDto;
	}

	/**
	 * This method is used to read all student details from DB
	 * 
	 * @return List of Student Dto bean objects with all details read from DB
	 */
	public List<StudentDetailsDto> getAllStudents() {
		List<StudentDetailsEntity> listStudentDetailsEntity = (List<StudentDetailsEntity>) studentRepository.findAll();

		return listStudentDetailsEntity.stream().map(studentDetailsEntity -> {
			StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
			BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
			return studentDetailsDto;
		}).collect(Collectors.toList());
	}

}
