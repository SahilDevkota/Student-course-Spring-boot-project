package com.example.project1.Tests;


import com.example.project1.DTO.CourseDTO;
import com.example.project1.DTO.EnrollmentDTO;
import com.example.project1.DTO.StudentDTO;
import com.example.project1.Service.CourseService;
import com.example.project1.Service.EnrollmentService;
import com.example.project1.Service.StudentService;
import com.example.project1.entity.CourseEntity;
import com.example.project1.entity.Enrollment;
import com.example.project1.entity.StudentEntity;
import com.example.project1.mapper.CourseMapper;
import com.example.project1.mapper.EnrollmentMapper;
import com.example.project1.mapper.StudentMapper;
import com.example.project1.repository.CourseRepository;
import com.example.project1.repository.EnrollmentRepository;
import com.example.project1.repository.StudentRepository;
import com.example.project1.utilMethod.TestDataUtilForCourse;
import com.example.project1.utilMethod.TestDataUtilForEnrollment;
import com.example.project1.utilMethod.TestDataUtilForStudent;
import org.checkerframework.checker.nonempty.qual.EnsuresNonEmpty;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class EnrollmentIntegrationTest {


    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private EnrollmentService enrollmentService;

    @Test
    public void testThatSpringBootEnrollsStudentInACourse() throws Exception{

        StudentDTO studentDTO = TestDataUtilForStudent.createStudentDTOA();
        studentService.addStudent(studentDTO);

        StudentEntity student = studentRepository.save(studentMapper.mapFrom(studentDTO));

        CourseDTO courseDTO = TestDataUtilForCourse.createCourseDTOa();
        courseService.addCourse(courseDTO);

        CourseEntity course = courseRepository.save(courseMapper.mapFrom(courseDTO));


        EnrollmentDTO enrollmentDTO = TestDataUtilForEnrollment.createEnrollmentDTOa();

        enrollmentDTO.setCourse_id(course.getCourse_id());
        enrollmentDTO.setStudent_id(student.getStudent_id());

        String enrollmentJSON = objectMapper.writeValueAsString(enrollmentDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/enrollments/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(enrollmentJSON)

        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testThatSpringBootReturnsAlistOfStudentEnrolledInACourse() throws Exception{

        StudentDTO studentDTOa = TestDataUtilForStudent.createStudentDTOA();
        studentService.addStudent(studentDTOa);

        StudentEntity studentA =  studentRepository.save(studentMapper.mapFrom(studentDTOa));

        StudentDTO studentDTOb = TestDataUtilForStudent.createStudentDTOB();
        studentService.addStudent(studentDTOb);
        StudentEntity studentB =  studentRepository.save(studentMapper.mapFrom(studentDTOb));;


        CourseDTO courseDTO = TestDataUtilForCourse.createCourseDTOa();
        courseService.addCourse(courseDTO);
        CourseEntity course =  courseRepository.save(courseMapper.mapFrom(courseDTO));
        courseRepository.save(course);

        Enrollment enrollmentA = new Enrollment();
        enrollmentA.setCourse(course);
        enrollmentA.setStudent(studentA);

        Enrollment enrollmentB = new Enrollment();
        enrollmentB.setStudent(studentB);
        enrollmentB.setCourse(course);

        enrollmentRepository.save(enrollmentA);

        enrollmentRepository.save(enrollmentB);

        EnrollmentDTO enrollmentDTOA = TestDataUtilForEnrollment.createEnrollmentDTOa();
        EnrollmentDTO enrollmentDTOB = TestDataUtilForEnrollment.createEnrollmentDTOb();

        enrollmentDTOA.setStudent_id(studentA.getStudent_id());
        enrollmentDTOA.setCourse_id(course.getCourse_id());

        enrollmentDTOB.setStudent_id(studentB.getStudent_id());
        enrollmentDTOB.setCourse_id(course.getCourse_id());





        mockMvc.perform(
                MockMvcRequestBuilders.get("/enrollments/allStudents/{courseID}",course.getCourse_id())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Sumen"));


    }


    @Test
    public void testThatSpringBootReturnsAListOfCourseAStudentIsEnrolledIn() throws Exception{


        CourseDTO courseDTOa = TestDataUtilForCourse.createCourseDTOa();
        CourseEntity courseA = courseRepository.save(courseMapper.mapFrom(courseDTOa));


        CourseDTO courseDTOb = TestDataUtilForCourse.createCourseDTOb();
        CourseEntity courseB = courseRepository.save(courseMapper.mapFrom(courseDTOb));


        StudentDTO studentDTO = TestDataUtilForStudent.createStudentDTOA();
        StudentEntity student = studentRepository.save(studentMapper.mapFrom(studentDTO));

        Enrollment enrollmentA = new Enrollment();
        enrollmentA.setCourse(courseA);
        enrollmentA.setStudent(student);

        Enrollment enrollmentB = new Enrollment();
        enrollmentB.setCourse(courseB);
        enrollmentB.setStudent(student);

        enrollmentRepository.save(enrollmentA);
        enrollmentRepository.save(enrollmentB);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/enrollments/allCourse/{studentID}",student.getStudent_id())
        ).andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("NbIT"));
    }






}
