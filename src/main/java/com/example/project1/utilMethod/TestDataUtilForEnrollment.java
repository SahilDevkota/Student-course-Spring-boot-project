package com.example.project1.utilMethod;


import com.example.project1.DTO.EnrollmentDTO;

/*Utility class to create sample enrollmentDTO for testing purpose */
public class TestDataUtilForEnrollment {

    //Creates a sample enrollmentDTO with student ID = 1 and course ID = 2.
    public static EnrollmentDTO createEnrollmentDTOa() {
        return EnrollmentDTO.builder()
                .course_id(2)
                .student_id(1)
                .build();

    }

    //Creates a sample enrollmentDTO with student ID = 1 and course ID = 2.
    public static EnrollmentDTO createEnrollmentDTOb() {
        return EnrollmentDTO.builder()
                .course_id(2)
                .student_id(1)
                .build();

    }
}
