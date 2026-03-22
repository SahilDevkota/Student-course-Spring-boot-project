package com.example.project1.Service;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.DTO.EnrollmentDTO;
import com.example.project1.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/* Service interface for managing enrollments
 * Provides methods to enroll student in a course, get a list of student enrolled in one course and get a list of course a student is enrolled in */
public interface EnrollmentService {

    //Enrolls a student in a course
    ResponseEntity<String> EnrollStudentInCourse(EnrollmentDTO enrollmentDTO);

    //Returns a list of student enrolled in one course identified by an ID
    List<StudentDTO> getAListOfStudentEnrolledInACourse(Integer courseID);

    //Returns a list of course enrolled by a student identified by an ID
    List<CourseDTO> getAListOfCoursesEnrolledByStudent(Integer studentId);
}
