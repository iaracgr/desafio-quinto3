package com.gonzalez.desafioquintoimpacto.service.abstraction;

import com.gonzalez.desafioquintoimpacto.dto.ProfessorDto;
import com.gonzalez.desafioquintoimpacto.dto.StudentDto;
import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.mapper.StudentMapper;
import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;

import java.util.Date;
import java.util.List;

public interface IStudentService {

    void create (StudentDto dto);

    void delete (String id) throws Exception;

    StudentDto updateUser(String id, String name, String email, String age, String history, Date birthday) throws Exception;

    StudentDto getByEmail (String email);

    StudentEntity getById (String id);

    List<StudentEntity> getAll();

    StudentDto addStudentToCourse(String idStudent, String idCourse) throws Exception;

    StudentDto removeStudentFromCourse(String idStudent, String idCourse) throws Exception;
}
