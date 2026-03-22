package com.example.project1.mapper;

import com.example.project1.DTO.EnrollmentDTO;
import com.example.project1.entity.Enrollment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/*EnrollmentMapper uses modelMapper which is used to map entity to a DTO and vice versa.*/

@Component
public class EnrollmentMapper {

    private ModelMapper modelMapper;

    /*EnrollmentMapper constructor*/
    public EnrollmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /*Maps Entity to DTO */
    public EnrollmentDTO mapTo(Enrollment enrollment){
        return modelMapper.map(enrollment, EnrollmentDTO.class);
    }

    /*Maps DTO to entity */
    public Enrollment mapFrom(EnrollmentDTO enrollmentDTO){
        return modelMapper.map(enrollmentDTO,Enrollment.class);
    }
}
