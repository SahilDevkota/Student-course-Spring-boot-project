package com.example.project1.ServiceImpl;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.DTO.EnrollmentDTO;
import com.example.project1.DTO.StudentDTO;
import com.example.project1.Service.EnrollmentService;
import com.example.project1.entity.CourseEntity;
import com.example.project1.entity.Enrollment;
import com.example.project1.entity.StudentEntity;
import com.example.project1.mapper.CourseMapper;
import com.example.project1.mapper.StudentMapper;
import com.example.project1.repository.CourseRepository;
import com.example.project1.repository.EnrollmentRepository;
import com.example.project1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

/*Implements EnrollmentService interface
* Provides business logic to enroll student in a course, fetching a list of student enrolled in a course
* and fetching a list of course a student is enrolled in */

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    //Constructor-based dependency injection
    public EnrollmentServiceImpl(StudentMapper studentMapper, CourseMapper courseMapper, EnrollmentRepository enrollmentRepository, StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentMapper = studentMapper;
        this.courseMapper = courseMapper;
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    //Enrolls a student in a course
    @Override
    public ResponseEntity<String> EnrollStudentInCourse(EnrollmentDTO enrollmentDTO) {
        StudentEntity student = studentRepository.findById(enrollmentDTO.getStudent_id()).orElseThrow(()->new IllegalArgumentException("invalid student id"));
        CourseEntity course = courseRepository.findById(enrollmentDTO.getCourse_id()).orElseThrow(()->new IllegalArgumentException("Invalid course id"));

        Enrollment enrollment = new Enrollment();
        enrollment.setCourse(course);
        enrollment.setStudent(student);
        enrollmentRepository.save(enrollment);

        return ResponseEntity.ok("Student successfully added in the course");
    }

    //Get a list of student enrolled in one course
    @Override
    public List<StudentDTO> getAListOfStudentEnrolledInACourse(Integer courseID) {
        CourseEntity course = courseRepository.findById(courseID).orElseThrow(()->new IllegalArgumentException("Not found"));

        List<Enrollment>  enrollmentList = enrollmentRepository.findAll();

        List<StudentDTO> studentDTOList = enrollmentList.stream()
                .filter(enrollment -> enrollment.getCourse().getCourse_id().equals(course.getCourse_id()))
                .map(enrollment ->
                        studentMapper.mapTO(enrollment.getStudent()))
                .toList();

        return studentDTOList;
    }

    //Get a list of course a student is enrolled in
    @Override
    public List<CourseDTO> getAListOfCoursesEnrolledByStudent(Integer studentId) {
        StudentEntity student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalArgumentException("Student not found"));

        List<Enrollment> enrollmentList = enrollmentRepository.findAll();

        List<CourseDTO> courseDTOList = enrollmentList.stream().
                filter(enrollment -> enrollment.getStudent().getStudent_id().equals(student.getStudent_id()))
                .map(enrollment -> courseMapper.mapTo(enrollment.getCourse())
                ).toList();

        return courseDTOList;
    }
}
