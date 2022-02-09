package com.ekalavya.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekalavya.student.dto.ResultDto;
import com.ekalavya.student.dto.StudentDetailsDto;
import com.ekalavya.student.service.StudentService;

/**
 * Controller class
 * 
 * @author Lukesh Bhendarker
 *
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private Environment environment;

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

	@GetMapping("/{id}")
	public ResponseEntity<StudentDetailsDto> getStudentById(@PathVariable int id) {
		StudentDetailsDto studentDetailsDto = studentService.getStudentByStudentId(id);
		return new ResponseEntity<StudentDetailsDto>(studentDetailsDto, HttpStatus.OK);
	}

	@GetMapping("/byParameter")
	public ResponseEntity<List<StudentDetailsDto>> getStudentsByParam(
			@RequestParam(name = "rollNumber", required = false) String rollNumber,
			@RequestParam(name = "studentClass", required = false) Integer studentClass,
			@RequestParam(name = "city", required = false) String city) {
		ResponseEntity<List<StudentDetailsDto>> response = null;
		List<StudentDetailsDto> listStudentDetailsDto = null;
		if (rollNumber != null) {
			listStudentDetailsDto = studentService.getStudentByRollNumber(rollNumber);
		} else if (studentClass != null && city != null) {
			listStudentDetailsDto = studentService.getStudentByClassAndCity(studentClass, city);
		}
		if (listStudentDetailsDto != null) {
			response = new ResponseEntity<List<StudentDetailsDto>>(listStudentDetailsDto, HttpStatus.OK);
		}
		return response;
	}

	
	@PutMapping("/updateSection")
	public ResponseEntity<String> updateSection(@RequestBody StudentDetailsDto studentDetailsDto) {
		studentService.updateSection(studentDetailsDto.getStudentId(), studentDetailsDto.getSection());
		return new ResponseEntity<String>("Student record Updated for student id " + studentDetailsDto.getStudentId(),
				HttpStatus.OK);
	}
	
	@GetMapping("/testLoadBalancer")
	public String testLoadBalancer() {
		return "Served by Port:::"+environment.getProperty("local.server.port");
	}
	
	
}
