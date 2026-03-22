package com.example.project1.repository;

import com.example.project1.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

/*Handles Database operation for managing enrollmentEntity
 * Provides CRUD operations and query capabilities for enrollment  */
public interface EnrollmentRepository extends JpaRepository<Enrollment,Integer> {
}
