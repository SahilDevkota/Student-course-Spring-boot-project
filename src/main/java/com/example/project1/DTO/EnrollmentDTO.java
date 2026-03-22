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
* Data Transfer Object for enrollment
* It is used to transfer enrollment data from controller to service.
*  */
public class EnrollmentDTO {

    //Unique Id for enrollment
    private Integer id;

    //Foreign key (StudentEntity)
    private Integer student_id;

    //Foreign Key (CourseEntity)
    private Integer course_id;
}
