package com.example.project1.Tests;


import com.example.project1.DTO.CourseDTO;
import com.example.project1.Service.CourseService;
import com.example.project1.entity.CourseEntity;
import com.example.project1.mapper.CourseMapper;
import com.example.project1.repository.CourseRepository;
import com.example.project1.utilMethod.TestDataUtilForCourse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class CourseIntegrationTest {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CourseService courseService;

    @Test
    public void testThatSpringBootsAddCourse() throws Exception{

        CourseDTO courseDTO = TestDataUtilForCourse.createCourseDTOa();

        String courseDTOJSON = objectMapper.writeValueAsString(courseDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/course/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(courseDTOJSON)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("NbIT"));
    }

    @Test
    public void testThatSpringBootReturnsAListOfCourse() throws Exception{


        CourseDTO courseDTOa = TestDataUtilForCourse.createCourseDTOa();


        CourseDTO courseDTOb = TestDataUtilForCourse.createCourseDTOb();


        courseService.addCourse(courseDTOa);
        courseService.addCourse(courseDTOb);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/course/all")
        ).andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2));
    }

    @Test
    public void testThatSpringBootUpdatesTheCourse() throws Exception {

        CourseDTO courseDTO = TestDataUtilForCourse.createCourseDTOa();
        courseDTO.setTitle("Bachelors of AI");

       CourseEntity saved = courseRepository.save(courseMapper.mapFrom(courseDTO));
       courseDTO.setCourse_id(saved.getCourse_id());
       courseService.updateCourse(courseDTO,courseDTO.getCourse_id());

        String UpdatedCourseDTOJson = objectMapper.writeValueAsString(courseDTO);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/course/{id}",courseDTO.getCourse_id())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UpdatedCourseDTOJson)
        ).andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bachelors of AI"));


    }

    @Test
    public void testThatSpringBootDeletesTheCourseWithAnID() throws Exception{
        CourseDTO courseDTO = TestDataUtilForCourse.createCourseDTOa();
        courseService.addCourse(courseDTO);

        CourseEntity saved = courseRepository.save(courseMapper.mapFrom(courseDTO));
        courseDTO.setCourse_id(saved.getCourse_id());

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/course/{id}",courseDTO.getCourse_id())
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}
