package com.ekalavya.studentapi.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekalavya.studentapi.dao.StudentDao;
import com.ekalavya.studentapi.dto.StudentDetailsDto;

/**
 * @author Lukesh Bhendaker
 *
 */
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;
	
	@Override
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {
		final Calendar calendar = Calendar.getInstance();
		studentDetailsDto.setCreatedDate(calendar.getTime());
		studentDetailsDto = studentDao.createStudent(studentDetailsDto);
		return studentDetailsDto;
	}

	@Override
	public List<StudentDetailsDto> getAllStudents() {
		return studentDao.getAllStudents();
	}

}
