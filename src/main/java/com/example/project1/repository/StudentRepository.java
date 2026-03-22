package com.example.project1.repository;

import com.example.project1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*Handles Database operation for managing studentEntity
 * Provides CRUD operations and query capabilities for student  */
public interface StudentRepository extends JpaRepository<StudentEntity,Integer> {

}
