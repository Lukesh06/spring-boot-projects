package com.ekalavya.studentapi.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.studentapi.student.dto.StudentDetailsDto;
import com.ekalavya.studentapi.student.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	/**
	 * This method is used to create student record in DB
	 * @param studentDetailsDto object of Student dto
	 * @return studentDetailsDto object of Student dto
	 */
	@PostMapping
	public ResponseEntity<StudentDetailsDto> createStudent(@RequestBody StudentDetailsDto studentDetailsDto) {
		studentDetailsDto = studentService.createStudent(studentDetailsDto);
		return new ResponseEntity<StudentDetailsDto>(studentDetailsDto, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<StudentDetailsDto>> getAllStudents(){
		List<StudentDetailsDto> listStudentDetailsDto = studentService.getAllStudents();
		return new ResponseEntity<List<StudentDetailsDto>>(listStudentDetailsDto, HttpStatus.OK);
	}
	

}
