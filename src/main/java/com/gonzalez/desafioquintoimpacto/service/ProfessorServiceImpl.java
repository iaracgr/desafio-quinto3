package com.gonzalez.desafioquintoimpacto.service;

import com.gonzalez.desafioquintoimpacto.config.RoleType;
import com.gonzalez.desafioquintoimpacto.dto.ProfessorDto;
import com.gonzalez.desafioquintoimpacto.mapper.ProfessorMapper;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.UserEntity;
import com.gonzalez.desafioquintoimpacto.repository.IProfessorRepository;
import com.gonzalez.desafioquintoimpacto.repository.IRoleRepository;
import com.gonzalez.desafioquintoimpacto.service.abstraction.IProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorServiceImpl implements IProfessorService {

    @Autowired
    private ProfessorMapper professorMapper;

    @Autowired
    private IProfessorRepository professorRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Lazy
    @Autowired
    private CourseServiceImpl courseService;

    @Override
    public void create(ProfessorDto dto) {
        ProfessorEntity professor = new ProfessorEntity();
        professor.setName(dto.getName());
        professor.setEmail(professor.getEmail());
        professor.setSurname(professor.getSurname());
        professor.setRoles(List.of((iRoleRepository.findByName(RoleType.PROFESSOR))));
        professorRepository.save(professor);
    }

    @Override
    public void delete(String id) throws Exception {
        ProfessorEntity professorEntity = professorRepository.findByProfessorIdAndSoftDeleteFalse(id);
        professorEntity.setSoftDelete(true);
        professorRepository.save(professorEntity);
    }

    @Override
    public ProfessorDto updateUser(String id, String name, String email, String surname) throws Exception {
        ProfessorEntity entity = professorRepository.findByProfessorId(id);
        if (entity != null) {
            if (name != null) {
                entity.setName(name);
            }
            if (email != null) {
                entity.setEmail(email);// todo: agregar validacion de email unico
            }
            if (surname != null){
                entity.setSurname(surname);
            }
            ProfessorDto professorDto= professorMapper.map(professorRepository.save(entity));
            return professorDto;
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public ProfessorDto getByEmail(String email) {
        ProfessorEntity professor = professorRepository.findByEmail(email);
        if (professor == null) {
            throw new EntityNotFoundException("User not found");
        }
        return professorMapper.map(professor);
    }

    @Override
    public ProfessorDto getById(String id) {
        ProfessorEntity professor = professorRepository.findByProfessorId(id);
        if (professor == null) {
            throw new EntityNotFoundException("User not found");
        }
        return professorMapper.map(professor);
    }

    @Override
    public List<ProfessorEntity> getAll() {
        List<ProfessorEntity> lista = new ArrayList<>();
        lista = professorRepository.findAll();
        return lista;
    }

    @Override
    public ProfessorDto addProfessorToCourse(String idProfessor, String idCourse) throws Exception {
        ProfessorEntity professor = professorRepository.findByProfessorId(idProfessor);
        CourseEntity course = courseService.getByIdAndSoftDeleteFalse(idCourse);
        if (course.getProfessorsList().contains(professor)) {
            throw new Exception("this professor is already assigned to this course.");
        }
        professor.addCourse(course);
        course.addProfessor(professor);
        return professorMapper.map(professor);
    }

    @Override
    public ProfessorDto removeProfessorFromCourse(String idProfessor, String idCourse) throws Exception {
        ProfessorEntity professor = professorRepository.findByProfessorId(idProfessor);
        CourseEntity course = courseService.getByIdAndSoftDeleteFalse(idCourse);
        if (!course.getProfessorsList().contains(professor)) {
            throw new Exception("this professor does not have this course.");
        }
        professor.removeCourse(course);
        course.removeProfessor(professor);
        return professorMapper.map(professor);
    }
}
