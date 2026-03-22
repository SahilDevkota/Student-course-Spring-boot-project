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
* Data Transfer Objects for course
* It is used to transfer course data between controller and service layer.
*   */
public class CourseDTO {

    //Unique ID for course
    private Integer course_id;

    //Name of the course
    private String title;

}
