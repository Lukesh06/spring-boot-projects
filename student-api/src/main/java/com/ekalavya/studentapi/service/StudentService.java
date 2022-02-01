package com.ekalavya.studentapi.service;

import java.util.List;

import com.ekalavya.studentapi.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
public interface StudentService {

	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto);
	
	public List<StudentDetailsDto> getAllStudents();
	
}
