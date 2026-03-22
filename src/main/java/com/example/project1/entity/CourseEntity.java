package com.example.project1.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course")
/*
* Represents a course in the system.
* This entity is mapped to "course" table in the database.
* A course can have many enrollments. (Students enrolled in it).
* */
public class CourseEntity {

    /* Primary Key for the course
    * Is auto-generated using a sequence generator*/
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    private Integer course_id;

    //Title of the course
    private String title;

    /*List of enrollments associated with the course
    * Represents the student enrolled in the course */
    @OneToMany(mappedBy = "course")
    List<Enrollment> enrollmentList;
}
