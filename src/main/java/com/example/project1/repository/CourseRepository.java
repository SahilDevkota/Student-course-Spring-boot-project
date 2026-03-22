package com.example.project1.repository;

import com.example.project1.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/*Handles Database operation for managing courseEntity
* Provides CRUD operations and query capabilities for course  */
public interface CourseRepository extends JpaRepository<CourseEntity,Integer> {
}
