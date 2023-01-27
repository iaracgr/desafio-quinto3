package com.gonzalez.desafioquintoimpacto.mapper;

import com.gonzalez.desafioquintoimpacto.dto.StudentDto;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    public StudentDto map(StudentEntity studentEntity) {
        StudentDto studentResponse = new StudentDto();
        studentResponse.setIdStudent(studentEntity.getStudentId());
        studentResponse.setName(studentEntity.getName());
        studentResponse.setEmail(studentEntity.getEmail());
        studentResponse.setAge(studentEntity.getAge());
        studentResponse.setBirthday(studentEntity.getBirthday());
        studentResponse.setHistory(studentEntity.getHistory());
        studentResponse.setSoftDelete(studentEntity.getSoftDelete());
        List<String> names = new ArrayList<>();
        List<CourseEntity> courses = studentEntity.getCourses();
        for (CourseEntity course : courses) {
            names.add(course.getName());
        }
        studentResponse.setCourses(names);
        return studentResponse;
    }

    public List<StudentDto> map(List<StudentEntity> students) {
        List<StudentDto> responses = new ArrayList<>(students.size());
        for (StudentEntity student : students) {
            responses.add(map(student));
        }
        return responses;
    }
}
