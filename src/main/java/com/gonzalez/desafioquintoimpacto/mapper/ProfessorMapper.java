package com.gonzalez.desafioquintoimpacto.mapper;

import com.gonzalez.desafioquintoimpacto.dto.ProfessorDto;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProfessorMapper {
    public ProfessorDto map (ProfessorEntity professorEntity){
        ProfessorDto response = new ProfessorDto();
        response.setIdProfessor(professorEntity.getProfessorId());
        response.setName(professorEntity.getName());
        response.setSurname(professorEntity.getSurname());
        response.setEmail(professorEntity.getEmail());
        response.setSoftDelete(professorEntity.getSoftDelete());
        List<String> names = new ArrayList<>();
        List<CourseEntity> courses = professorEntity.getCourses();
        for (CourseEntity course : courses){
            names.add(course.getName());
        }
        response.setCourses(names);
        return response;
    }



    public List<ProfessorDto> map (List<ProfessorEntity> professors){
        List<ProfessorDto> list = new ArrayList<>(professors.size());
        for (ProfessorEntity professor : professors){
            list.add(map(professor));
        }
        return list;
    }
}
