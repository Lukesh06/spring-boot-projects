package com.ekalavya.student.dao;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ekalavya.student.dto.StudentDetailsDto;
import com.ekalavya.student.entity.StudentDetailsEntity;
import com.ekalavya.student.repository.StudentRepository;

/**
 * Dao class to perform DB operations
 * 
 * @author Lukesh Bhendaker
 *
 */
@Component
public class StudentDao {

	@Autowired
	private StudentRepository studentRepository;

	/**
	 * This method is used to save student records in DB
	 * 
	 * @param studentDetailsDto DTO bean object with student details to save
	 * @return studentDetailsDto DTO bean object after save
	 */
	public StudentDetailsDto createStudent(StudentDetailsDto studentDetailsDto) {
		StudentDetailsEntity studentDetailsEntity = new StudentDetailsEntity();
		BeanUtils.copyProperties(studentDetailsDto, studentDetailsEntity);
		TimeZone timeZone = TimeZone.getTimeZone("IST");
		final Calendar calendar = Calendar.getInstance(timeZone);
		studentDetailsEntity.setCreatedDate(calendar.getTime());
		studentDetailsEntity = studentRepository.save(studentDetailsEntity);
		BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
		populateDate(studentDetailsEntity, studentDetailsDto);
		return studentDetailsDto;
	}

	private void populateDate(StudentDetailsEntity studentDetailsEntity, StudentDetailsDto studentDetailsDto) {
		String createdDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(studentDetailsEntity.getCreatedDate());
		studentDetailsDto.setCreatedDate(createdDate);
	}

	/**
	 * This method is used to read all student details from DB
	 * 
	 * @return List of Student Dto bean objects with all details read from DB
	 */
	public List<StudentDetailsDto> getAllStudents() {
		List<StudentDetailsEntity> listStudentDetailsEntity = (List<StudentDetailsEntity>) studentRepository.findAll();

		return prepareListOfDto(listStudentDetailsEntity);
	}

	public StudentDetailsDto getStudentByStudentId(int studentId) {
		Optional<StudentDetailsEntity> optionalEntity = studentRepository.findById(studentId);
		StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
		if (optionalEntity.isPresent()) {
			BeanUtils.copyProperties(optionalEntity.get(), studentDetailsDto);
			populateDate(optionalEntity.get(), studentDetailsDto);
		}
		return studentDetailsDto;
	}

	public List<StudentDetailsDto> getStudentByRollNumber(String rollNumber) {
		List<StudentDetailsEntity> listStudentDetailsEntity = (List<StudentDetailsEntity>) studentRepository
				.findByRollNumber(rollNumber);
		return prepareListOfDto(listStudentDetailsEntity);
	}

	public List<StudentDetailsDto> getStudentByClassAndCity(int studentClass, String city) {
		List<StudentDetailsEntity> listStudentDetailsEntity = (List<StudentDetailsEntity>) studentRepository
				.findByStudentClassAndCityOrderByNameAsc(studentClass, city);
		return prepareListOfDto(listStudentDetailsEntity);
	}

	public void updateSection(int studentId, String section) {
		studentRepository.updateSectionForStudentId(studentId, section);
	}

	private List<StudentDetailsDto> prepareListOfDto(List<StudentDetailsEntity> listStudentDetailsEntity) {
		return listStudentDetailsEntity.stream().map(studentDetailsEntity -> {
			StudentDetailsDto studentDetailsDto = new StudentDetailsDto();
			BeanUtils.copyProperties(studentDetailsEntity, studentDetailsDto);
			populateDate(studentDetailsEntity, studentDetailsDto);
			return studentDetailsDto;
		}).collect(Collectors.toList());
	}

}
