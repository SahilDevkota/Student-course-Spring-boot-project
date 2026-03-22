package com.example.project1.Service;

import com.example.project1.DTO.StudentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/* Service interface for managing student
 * Provides methods to add, fetch, update and delete student */
public interface StudentService {

    //Adds a student to a database
    ResponseEntity<String> addStudent(StudentDTO studentDTO);

    //Fetch a student from database using an ID
    ResponseEntity<StudentDTO> getStudent(Integer id);

    //Get a list of students from database
    List<StudentDTO> getAllStudents();

    //Update an existing student identified by an ID
    ResponseEntity<StudentDTO> updateStudent(Integer id, StudentDTO studentDTO);

    //Delete a student from database using their ID
    ResponseEntity<String> deleteStudentByID(Integer id);
}
