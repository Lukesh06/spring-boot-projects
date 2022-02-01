package com.ekalavya.student.service;

import java.util.List;

import com.ekalavya.student.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
public interface StudentService {

	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto);
	
	public List<StudentDetailsDto> getAllStudents();
	
}
