package com.example.project1.controller;


import com.example.project1.DTO.CourseDTO;
import com.example.project1.ServiceImpl.CourseServiceImpl;
import com.example.project1.entity.CourseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/course")

//Controller Class for managing courses
//Provides APIs to add, update, fetch and delete courses.
public class courseController {

    @Autowired
    private CourseServiceImpl courseServiceImpl;


    //courseController constructor
    public courseController(CourseServiceImpl courseServiceImpl) {
        this.courseServiceImpl = courseServiceImpl;
    }

    //Add a new course to the database
    //@Param courseDTO: Data of course to add
    @PostMapping("/add")
    public ResponseEntity<CourseEntity> addCourse(@RequestBody CourseDTO courseDTO){
        return courseServiceImpl.addCourse(courseDTO);
    }

    //Get a list of course from the database
    @GetMapping("/all")
    public ResponseEntity<List<CourseDTO>> getAllCourses(){
        return courseServiceImpl.getAllCourses();
    }

    //Update course by ID.
    //@Param id : ID of course
    //Param courseDTO : Data of course to update
    //returns updated course
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTO> updateCourse(@PathVariable Integer id, @RequestBody CourseDTO courseDTO){
        return courseServiceImpl.updateCourse(courseDTO,id);
    }

    //Delete a course by an ID.
    //@Param id : ID of course
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
       return courseServiceImpl.deleteCourse(id);
    }
}
