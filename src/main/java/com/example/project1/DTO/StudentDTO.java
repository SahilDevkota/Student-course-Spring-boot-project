package com.example.project1.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
/*
* Data Transfer Object for student
* It is used to transfer student data from controller to service layer.
* */


public class StudentDTO {

    //Unique ID for student
    private Integer student_id;

    //Name of the student
    private String name;

    //Email of the student
    private String email;

}
