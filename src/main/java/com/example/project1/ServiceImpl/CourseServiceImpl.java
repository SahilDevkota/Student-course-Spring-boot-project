package com.example.project1.ServiceImpl;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.Service.CourseService;
import com.example.project1.entity.CourseEntity;
import com.example.project1.mapper.CourseMapper;
import com.example.project1.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
/* Implementation of courseService interface
* Provide business logic for adding, fetching, updating and deleting course*/

public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    //Constructor-based dependency injection
    public CourseServiceImpl(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    //Add a new course to the database
    @Override
    public ResponseEntity<CourseEntity> addCourse(CourseDTO courseDTO) {
        CourseEntity course = courseMapper.mapFrom(courseDTO);
        courseRepository.save(course);

        return ResponseEntity.ok(course);
    }

    //Get a list of course from the database
    @Override
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courseList = courseRepository.findAll().stream()
                .map(courseMapper::mapTo)
                .toList();

        if(courseList.isEmpty()){
            return ResponseEntity.ok(courseList);
        }else {
            return ResponseEntity.ok(courseList);
        }
    }

    //returns updated course
    @Override
    public ResponseEntity<CourseDTO> updateCourse(CourseDTO courseDTO,Integer id) {

        CourseEntity course = courseRepository.findById(id).orElse(null);

        if(courseRepository.existsById(id)){
            course.setTitle(courseDTO.getTitle());
            courseRepository.save(course);
            CourseDTO courseDTO1 = courseMapper.mapTo(course);
            return ResponseEntity.ok(courseDTO1);
        }
        else{
            return ResponseEntity.status(404).build();
        }
    }

    //Delete a course by an ID.
    @Override
    public ResponseEntity<String> deleteCourse(Integer id) {
        {
            if(courseRepository.existsById(id)){
                courseRepository.deleteById(id);
                return ResponseEntity.ok("Student deleted successfully");
            }else{
                return ResponseEntity.status(404).body("Student Not found!");
            }
        }

    }
}
