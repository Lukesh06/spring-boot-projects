package com.ekalavya.studentapi.student.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ekalavya.studentapi.student.entity.StudentDetailsEntity;

@Repository
public interface StudentRepository extends CrudRepository<StudentDetailsEntity, Integer> {

}
