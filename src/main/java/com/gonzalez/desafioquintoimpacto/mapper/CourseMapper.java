package com.gonzalez.desafioquintoimpacto.mapper;

import com.gonzalez.desafioquintoimpacto.dto.CourseDto;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {

    public CourseDto map(CourseEntity course) {
        CourseDto courseResponse = new CourseDto();
        courseResponse.setId(course.getCourseId());
        courseResponse.setName(course.getName());
        courseResponse.setDaytime(course.getDaytime());
        courseResponse.setSchedule(course.getSchedule());
        courseResponse.setSoftDelete(course.getSoftDelete());
        List<String> names = new ArrayList<>();
        List<ProfessorEntity> professors = course.getProfessorsList();
        for (ProfessorEntity professor : professors){
            names.add(professor.getName());
        }
        courseResponse.setProfessorsNames(names);
        List<String> studentsNames = new ArrayList<>();
        List<StudentEntity> students = course.getStudentsList();
        for (StudentEntity student : students){
            studentsNames.add(student.getName());
        }
        courseResponse.setStudentsNames(studentsNames);
        return courseResponse;
    }

    public List<CourseDto> map(List<CourseEntity> courses) {
        List<CourseDto> courseResponses = new ArrayList<>(courses.size());
        for (CourseEntity course : courses) {
            courseResponses.add(map(course));
        }
        return courseResponses;
    }
}
