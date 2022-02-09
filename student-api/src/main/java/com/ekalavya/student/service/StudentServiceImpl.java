package com.ekalavya.student.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ekalavya.student.dao.StudentDao;
import com.ekalavya.student.dto.ResultDto;
import com.ekalavya.student.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ResultApiClient resultApiClient;

	@Autowired
	private Environment environment;

	/**
	 * This method is used to save student records in DB
	 * 
	 * @param studentDetailsDto DTO bean object with student details to save
	 * @return studentDetailsDto DTO bean object after save
	 */
	@Override
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {

		studentDetailsDto = studentDao.createStudent(studentDetailsDto);

		return studentDetailsDto;
	}

	/**
	 * This method is used to read all student details from DB
	 * 
	 * @return List of Student Dto bean objects with all details read from DB
	 */
	@Override
	public List<StudentDetailsDto> getAllStudents() {
		return studentDao.getAllStudents();
	}

	@Override
	public StudentDetailsDto getStudentByStudentId(int studentId) {
		List<ResultDto> listResult = getResultByStudentId(studentId);
		StudentDetailsDto studentDto = studentDao.getStudentByStudentId(studentId);
		studentDto.setResultDto(listResult);
		return studentDto;
	}

	@Override
	public List<StudentDetailsDto> getStudentByRollNumber(String rollNumber) {
		return studentDao.getStudentByRollNumber(rollNumber);
	}

	@Override
	public List<StudentDetailsDto> getStudentByClassAndCity(int studentClass, String city) {
		return studentDao.getStudentByClassAndCity(studentClass, city);
	}

	@Override
	public void updateSection(int studentId, String section) {
		studentDao.updateSection(studentId, section);

	}

	public List<ResultDto> getResultByStudentId_RestTemplate(int studentId) {
		String resultServiceUrl = String.format(environment.getProperty("result.api.url"), studentId);
		ResponseEntity<List<ResultDto>> responseResult = restTemplate.exchange(resultServiceUrl, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ResultDto>>() {
				});
		return responseResult.getBody();
	}

	@Override
	public List<ResultDto> getResultByStudentId(int studentId) {
		ResponseEntity<List<ResultDto>> responseResult = resultApiClient.searchByStudentId(studentId);
		return responseResult.getBody();
	}

}
