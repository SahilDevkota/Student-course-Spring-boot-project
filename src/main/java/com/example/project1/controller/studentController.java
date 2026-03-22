package com.example.project1.controller;

import com.example.project1.DTO.StudentDTO;
import com.example.project1.ServiceImpl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")

//Student controller for managing student
//Provides APIs to add, fetch , update and delete students.
public class studentController {

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    //Constructor-based dependency injection
    public studentController(StudentServiceImpl studentServiceImpl) {
        this.studentServiceImpl = studentServiceImpl;

    }

    //Adding a new student in a database
    //@Param studentDTO : Data of student to add
    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody StudentDTO studentDTO) {
        return studentServiceImpl.addStudent(studentDTO);
    }

    //Returns a student with a requested id.
    //@Param id: students' ID
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Integer id) {
        return studentServiceImpl.getStudent(id);
    }

    //Returns a list of student
    @GetMapping("/all")
    public List<StudentDTO> getAllStudents() {
        return studentServiceImpl.getAllStudents();
    }

    //Updates the student in the database using studentID
    //@Param id : Students' ID
    //@Param studentDTO : Data of student to be fetched
    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable Integer id, @RequestBody StudentDTO studentDTO) {
        return studentServiceImpl.updateStudent(id, studentDTO);
    }

    //Deletes a student by an ID.
    //@Param id: Students' ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudentByID(@PathVariable Integer id) {
        return studentServiceImpl.deleteStudentByID(id);

    }
}
