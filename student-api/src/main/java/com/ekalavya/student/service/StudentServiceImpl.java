package com.ekalavya.student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ekalavya.student.Exception.StudentResultException;
import com.ekalavya.student.dao.StudentDao;
import com.ekalavya.student.dto.ResultDto;
import com.ekalavya.student.dto.StudentDetailsDto;

import feign.FeignException.FeignClientException;

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

	
	public List<ResultDto> getResultByStudentId_Rest_Template(int studentId) {
		String resultServiceUrl = String.format(environment.getProperty("result.api.url"), studentId);
		ResponseEntity<List<ResultDto>> responseResult = null;
		try {
			responseResult = restTemplate.exchange(resultServiceUrl, HttpMethod.GET, null,
					new ParameterizedTypeReference<List<ResultDto>>() {
					});
		} catch (Exception ex) {
			List<ResultDto> listResultDto = new ArrayList<ResultDto>();
			ResultDto resultDto = new ResultDto();
			resultDto.setErrorMessage(ex.getMessage());
			listResultDto.add(resultDto);
			responseResult = new ResponseEntity<List<ResultDto>>(listResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return responseResult.getBody();
	}

	@Override	
	public List<ResultDto> getResultByStudentId(int studentId) {

		ResponseEntity<List<ResultDto>> responseResult = null;
		String errorMessage = "Internal Server Error";
		
		try {
			responseResult = resultApiClient.searchByStudentId(studentId);
		} catch (FeignClientException ex) {
			List<ResultDto> listResultDto = new ArrayList<ResultDto>();
			ResultDto resultDto = new ResultDto();
			if(ex.status()==404) {
				errorMessage = "Result API is not responding. Please try after some time.";
			}else if(ex.status()==500) {
				errorMessage = "Internal server error, Please contact technical support team.";
			}
			resultDto.setErrorMessage(errorMessage);
			listResultDto.add(resultDto);
			responseResult = new ResponseEntity<List<ResultDto>>(listResultDto, HttpStatus.OK);
		}
		return responseResult.getBody();
	}

}
