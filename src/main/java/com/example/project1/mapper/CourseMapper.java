package com.example.project1.mapper;

import com.example.project1.DTO.CourseDTO;
import com.example.project1.entity.CourseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/*CourseMapper uses modelMapper which is used to map entity to a DTO and vice versa.*/
@Component
public class CourseMapper {

    private ModelMapper modelMapper;

    /*CourseMapper constructor*/
    public CourseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /*Maps Entity to DTO */
    public CourseDTO mapTo(CourseEntity course){
        return modelMapper.map(course, CourseDTO.class);
    }

    /*Maps DTO to entity */
    public CourseEntity mapFrom(CourseDTO courseDTO){
        return modelMapper.map(courseDTO,CourseEntity.class);
    }
}
