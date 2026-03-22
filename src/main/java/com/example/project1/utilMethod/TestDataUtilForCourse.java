package com.example.project1.utilMethod;

import com.example.project1.DTO.CourseDTO;

/*Utility class to create sample CoursDTO for testing purpose */
public class TestDataUtilForCourse {

    //Creates a sample courseDTO with title "NbIT"
    public static CourseDTO createCourseDTOa() {
        return  CourseDTO.builder()
                .title("NbIT")
                .build();

    }

    //Creates a sample courseDTO with title "Bachelors of IT"
    public static CourseDTO createCourseDTOb() {
        return  CourseDTO.builder()
                .title("Bachelors of IT")
                .build();

    }
}
