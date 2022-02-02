package com.ekalavya.student.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalavya.student.dao.StudentDao;
import com.ekalavya.student.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	/**
	 * This method is used to save student records in DB
	 * 
	 * @param studentDetailsDto DTO bean object with student details to save
	 * @return studentDetailsDto DTO bean object after save
	 */
	@Override
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {
		final Calendar calendar = Calendar.getInstance();
		studentDetailsDto.setCreatedDate(calendar.getTime());
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

}
