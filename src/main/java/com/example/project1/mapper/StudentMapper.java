package com.example.project1.mapper;


import com.example.project1.DTO.StudentDTO;
import com.example.project1.entity.StudentEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/*StudentMapper uses modelMapper which is used to map entity to a DTO and vice versa.*/
@Component
public class StudentMapper {


    private ModelMapper modelMapper;

    /*StudentMapper constructor*/
    public StudentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    /*Maps Entity to DTO */
    public StudentDTO mapTO(StudentEntity student){
        return modelMapper.map(student,StudentDTO.class);
    }

    /*Maps DTO to entity */
    public StudentEntity mapFrom(StudentDTO studentDTO){
        return modelMapper.map(studentDTO, StudentEntity.class);
    }
}
