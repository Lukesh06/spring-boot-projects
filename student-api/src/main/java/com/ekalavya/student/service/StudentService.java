package com.ekalavya.student.service;

import java.util.List;

import com.ekalavya.student.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
public interface StudentService {

	/**
	 * This method is used to save student records in DB
	 * 
	 * @param studentDetailsDto DTO bean object with student details to save
	 * @return studentDetailsDto DTO bean object after save
	 */
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto);

	/**
	 * This method is used to read all student details from DB
	 * 
	 * @return List of Student Dto bean objects with all details read from DB
	 */
	public List<StudentDetailsDto> getAllStudents();

}
