package com.gonzalez.desafioquintoimpacto.service.abstraction;

import com.gonzalez.desafioquintoimpacto.dto.ProfessorDto;
import com.gonzalez.desafioquintoimpacto.dto.UserDto;

import java.util.Date;
import java.util.List;

public interface IStudentService {

    void create (UserDto dto);

    void delete (String id) throws Exception;

    UserDto updateUser(String id, String name, String email, String age, String history, Date birthday) throws Exception;

    UserDto getByEmail (String email);

    UserDto getById (String id);

    List<UserDto> getAll();

    UserDto addStudentToCourse(String idStudent, String idCourse) throws Exception;

    UserDto removeStudentrFromCourse(String idStudent, String idCourse) throws Exception;
}
