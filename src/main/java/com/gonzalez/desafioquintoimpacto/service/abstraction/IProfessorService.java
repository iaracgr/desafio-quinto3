package com.gonzalez.desafioquintoimpacto.service.abstraction;

import com.gonzalez.desafioquintoimpacto.dto.ProfessorDto;
import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;

import java.util.List;

public interface IProfessorService {

    void create (ProfessorDto dto);

    void delete (String id) throws Exception;

    ProfessorDto updateUser( String id,String name, String email,String surname) throws Exception;

    ProfessorDto getByEmail (String email);

    ProfessorDto getById (String id);

    List<ProfessorEntity> getAll();

    ProfessorDto addProfessorToCourse(String idProfessor, String idCourse) throws Exception;

    ProfessorDto removeProfessorFromCourse(String idProfessor, String idCourse) throws Exception;
}
