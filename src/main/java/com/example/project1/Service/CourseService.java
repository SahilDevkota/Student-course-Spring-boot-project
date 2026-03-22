package com.example.project1.Service;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.entity.CourseEntity;
import org.springframework.http.ResponseEntity;


import java.util.List;

/* Service interface for managing courses
* Provides methods to add, fetch, update and delete course */
public interface CourseService {

    //Adds a new course to the database
    ResponseEntity<CourseEntity> addCourse(CourseDTO courseDTO);

    //Get a list of courses from the database
    ResponseEntity<List<CourseDTO>> getAllCourses();

    //Update an existing coursed identified by an ID
    ResponseEntity<CourseDTO> updateCourse(CourseDTO courseDTO,Integer id);

    //Deletes a course from database using its ID
    ResponseEntity<String> deleteCourse(Integer id);


}

