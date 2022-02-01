package com.ekalavya.studentapi.student.service;

import java.util.List;

import com.ekalavya.studentapi.student.dto.StudentDetailsDto;

public interface StudentService {

	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto);
	
	public List<StudentDetailsDto> getAllStudents();
	
}
