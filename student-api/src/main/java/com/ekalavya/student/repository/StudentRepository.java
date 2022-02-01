package com.ekalavya.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekalavya.student.entity.StudentDetailsEntity;

/**
 * @author Lukesh Bhendaker	
 *
 */
@Repository
public interface StudentRepository extends CrudRepository<StudentDetailsEntity, Integer> {

}
