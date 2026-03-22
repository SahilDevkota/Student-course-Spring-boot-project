package com.example.project1.controller;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.DTO.EnrollmentDTO;
import com.example.project1.DTO.StudentDTO;
import com.example.project1.ServiceImpl.EnrollmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/enrollments")
@RestController

//Enrollment controller for managing enrollments.
//Provides APIs to add and fetch enrollments.

public class EnrollmentController {

    @Autowired
    private EnrollmentServiceImpl enrollmentServiceImpl;

    //Constructor-based dependency injection
    public EnrollmentController(EnrollmentServiceImpl enrollmentService) {
       this.enrollmentServiceImpl = enrollmentService;
    }

    //Enrolls a student in a course
    @PostMapping("/add")
    public ResponseEntity<String> EnrollStudentInCourse(@RequestBody EnrollmentDTO enrollmentDTO){
        return enrollmentServiceImpl.EnrollStudentInCourse(enrollmentDTO);

    }

    //Get a list of student enrolled in one course
    //@Param id : course ID
    @GetMapping("allStudents/{courseID}")
    public List<StudentDTO> getAListOfStudentInACourse(@PathVariable Integer courseID){
        return enrollmentServiceImpl.getAListOfStudentEnrolledInACourse(courseID);

    }

    //Get a list of course a student is enrolled in
    //@Param id: ID of student
    @GetMapping("/allCourse/{studentId}")
    public List<CourseDTO> getAListofCourseEnrolledByStudent(@PathVariable Integer studentId){
        return enrollmentServiceImpl.getAListOfCoursesEnrolledByStudent(studentId);
    }

}
