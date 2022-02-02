package com.ekalavya.student.repository;

import org.springframework.data.repository.CrudRepository;
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

}
