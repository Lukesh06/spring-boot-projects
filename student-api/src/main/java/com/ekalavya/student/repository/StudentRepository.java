package com.ekalavya.student.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ekalavya.student.entity.StudentDetailsEntity;

/**
 * Repository Class to perform DB operatons
 * 
 * @author Lukesh Bhendaker
 *
 */
@Repository
public interface StudentRepository extends CrudRepository<StudentDetailsEntity, Integer> {

	List<StudentDetailsEntity>findByRollNumber(String rollNumber);
	
	List<StudentDetailsEntity>findByStudentClassAndCityOrderByNameAsc(int studentClass, String city);
	
	@Transactional
	@Modifying
	@Query(value="Update STUDENT_DETAILS set SECTION = :section where STUDENT_ID = :studentId",nativeQuery = true)
	void updateSectionForStudentId(@Param("studentId") int studentId, @Param("section") String section);
	
}
