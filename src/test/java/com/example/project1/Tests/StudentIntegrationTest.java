package com.example.project1.Tests;


import com.example.project1.DTO.StudentDTO;
import com.example.project1.Service.StudentService;
import com.example.project1.entity.StudentEntity;
import com.example.project1.mapper.StudentMapper;
import com.example.project1.repository.StudentRepository;
import com.example.project1.utilMethod.TestDataUtilForStudent;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@AutoConfigureMockMvc

public class StudentIntegrationTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentService studentService;



    @Test
    public void testThatSpringBootAddsStudent() throws Exception{
        StudentDTO studentDTO = TestDataUtilForStudent.createStudentDTOA();

        studentService.addStudent(studentDTO);

        String studentDtoJSON = objectMapper.writeValueAsString(studentDTO);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/students/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentDtoJSON)


        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    public void testThatSpringBootReturnsStudentWithGivenID() throws Exception{
        StudentDTO studentDTO = TestDataUtilForStudent.createStudentDTOA();
        studentService.addStudent(studentDTO);

       StudentEntity student = studentRepository.save(studentMapper.mapFrom(studentDTO));


        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/{id}",student.getStudent_id())
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sahil")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.email").value("SahilBdevkota@GMAIL.COM")

        ) .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatSpringBootReturnsAListOfStudents() throws Exception{
        StudentDTO studentDTOa = TestDataUtilForStudent.createStudentDTOA();
        StudentDTO studentDTOb = TestDataUtilForStudent.createStudentDTOB();

        studentService.addStudent(studentDTOa);
        studentService.addStudent(studentDTOb);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/students/all")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.length()").value(2)
        );
    }

    @Test
    public void testThatSpringBootUpdatesTheNameOfStudent() throws  Exception{
        StudentDTO studentDTOa = TestDataUtilForStudent.createStudentDTOA();
        studentDTOa.setName("Sumen");
        studentService.addStudent(studentDTOa);

        StudentEntity student = studentRepository.save(studentMapper.mapFrom(studentDTOa));
        studentDTOa.setStudent_id(student.getStudent_id());
        studentService.updateStudent(studentDTOa.getStudent_id(),studentDTOa);


        String studentJSON = objectMapper.writeValueAsString(studentDTOa);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/students/{id}", student.getStudent_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentJSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Sumen"));


    }

    @Test
    public void testThatSpringBootDeletesTheStudent() throws Exception{
        StudentDTO studentDTO = TestDataUtilForStudent.createStudentDTOA();

        studentService.addStudent(studentDTO);

        StudentEntity student = studentRepository.save(studentMapper.mapFrom(studentDTO));
        studentDTO.setStudent_id(student.getStudent_id());



        mockMvc.perform(
                MockMvcRequestBuilders.delete("/students/{id}", student.getStudent_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

}
