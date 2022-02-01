package com.ekalavya.studentapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.studentapi.dto.StudentDetailsDto;
import com.ekalavya.studentapi.service.StudentService;

/**
 * Controller class
 * @author Lukesh Bhendarker
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * This method is used to create student record in DB
	 * 
	 * @param studentDetailsDto object of Student dto
	 * @return Response Entity of type studentDetailsDto Student details bean
	 */
	@PostMapping
	public ResponseEntity<StudentDetailsDto> createStudent(@RequestBody StudentDetailsDto studentDetailsDto) {
		studentDetailsDto = studentService.createStudent(studentDetailsDto);
		return new ResponseEntity<StudentDetailsDto>(studentDetailsDto, HttpStatus.OK);
	}

	/**
	 * This method is used to get all students data
	 * 
	 * @return Response Entity of type list of studentDetailsDto Student details
	 *         bean list
	 */
	@GetMapping
	public ResponseEntity<List<StudentDetailsDto>> getAllStudents() {
		List<StudentDetailsDto> listStudentDetailsDto = studentService.getAllStudents();
		return new ResponseEntity<List<StudentDetailsDto>>(listStudentDetailsDto, HttpStatus.OK);
	}

}
