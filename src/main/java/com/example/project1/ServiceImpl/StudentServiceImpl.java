package com.example.project1.ServiceImpl;

import com.example.project1.DTO.StudentDTO;
import com.example.project1.Service.StudentService;
import com.example.project1.entity.StudentEntity;
import com.example.project1.mapper.StudentMapper;
import com.example.project1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
/*Implementing StudentService interface
* Provides business logic for adding,fetching, updating and deleting a student */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentRepository studentRepository;

    //Constructor-based dependency injection
    public StudentServiceImpl(StudentMapper studentMapper, StudentRepository studentRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
    }

    //Adding a new student in a database
    @Override
    public ResponseEntity<String> addStudent(StudentDTO studentDTO) {
        StudentEntity student = studentMapper.mapFrom(studentDTO);
        studentRepository.save(student);
        return ResponseEntity.ok("Student added Successfully");
    }

    //Returns a student with a requested id.
    @Override
    public ResponseEntity<StudentDTO> getStudent(Integer id) {
        return studentRepository.findById(id)
                .map(studentEntity -> studentMapper.mapTO(studentEntity))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //Returns a list of student
    @Override
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream().map(studentMapper::mapTO)
                .toList();

    }

    //Updates the student in the database using studentID
    @Override
    public ResponseEntity<StudentDTO> updateStudent(Integer id, StudentDTO studentDTO) {

        StudentEntity student = studentRepository.findById(id).orElse(null);

        if(studentRepository.existsById(id)){
            student.setName(studentDTO.getName());
            studentRepository.save(student);
            StudentDTO studentDTO1 = studentMapper.mapTO(student);
            return ResponseEntity.ok(studentDTO1);
        }else{
            return ResponseEntity.status(404).build();
        }
    }

    //Deletes a student by an ID.
    @Override
    public ResponseEntity<String> deleteStudentByID(Integer id) {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return ResponseEntity.status(200).body("Student with id " + id +" successfully deleted");
        }
        else{
            return ResponseEntity.status(404).body("Student with id " + id + " not found ");
        }
    }

}
