package com.example.project1.utilMethod;

import com.example.project1.DTO.StudentDTO;

/*Utility class to create sample studentDTO for testing purpose */
public class TestDataUtilForStudent {

    //Creates a sample studentDTO with name "Sahil" and email "Sahil@GMAIL.com"
    public static StudentDTO createStudentDTOA() {
        return  StudentDTO.builder()
                .name("Sahil")
                .email("Sahil@GMAIL.COM")
                .build();

    }

    //Creates a sample courseDTO with name "Sumen" and email "sumen@GMAIL.COM"
    public static StudentDTO createStudentDTOB() {
        return  StudentDTO.builder()
                .name("Sumen")
                .email("sumen@GMAIL.COM")
                .build();

    }
}
