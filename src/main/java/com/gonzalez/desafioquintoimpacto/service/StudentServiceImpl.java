package com.gonzalez.desafioquintoimpacto.service;

import com.gonzalez.desafioquintoimpacto.config.RoleType;
import com.gonzalez.desafioquintoimpacto.dto.StudentDto;
import com.gonzalez.desafioquintoimpacto.dto.UserDto;
import com.gonzalez.desafioquintoimpacto.mapper.StudentMapper;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;
import com.gonzalez.desafioquintoimpacto.repository.IRoleRepository;
import com.gonzalez.desafioquintoimpacto.repository.IStudentRepository;
import com.gonzalez.desafioquintoimpacto.service.abstraction.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements IStudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private IStudentRepository studentRepository;

    @Autowired
    private IRoleRepository iRoleRepository;

    @Lazy
    @Autowired
    private CourseServiceImpl courseService;

    @Override
    @Transactional
    public void create(StudentDto dto) {
        StudentEntity student = new StudentEntity();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setAge(dto.getAge());
        student.setHistory(dto.getHistory());
        student.setBirthday(dto.getBirthday());
        student.setRoles(List.of((iRoleRepository.findByName(RoleType.STUDENT))));
        studentRepository.save(student);
    }

    @Override
    public void delete(String id) throws Exception {
        StudentEntity student = studentRepository.findByStudentIdAndSoftDeleteFalse(id);
        student.setSoftDelete(true);
        studentRepository.save(student);
    }

    @Override
    public StudentDto updateUser(String id, String name, String email, String age, String history, Date birthday) throws Exception {
        Optional<StudentEntity> student = studentRepository.findById(id);
        if (student.isPresent()) {
            StudentEntity newStudent = new StudentEntity();
            if (name != null) {
                newStudent.setName(name);
            }
            if (email != null) {
                newStudent.setEmail(email);// todo: agregar validacion de email unico
            }
            if (age != null) {
                newStudent.setAge(age);
            }
            if (birthday != null) {
                newStudent.setBirthday(birthday);
            }
            if (history != null) {
                newStudent.setHistory(history);
            }
            StudentDto dto = studentMapper.map(studentRepository.save(newStudent));
            return dto;
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public StudentDto getByEmail(String email) {
        StudentEntity student = studentRepository.findByEmailAndSoftDeleteFalse(email);
        if (student == null) {
            throw new EntityNotFoundException("User not found");
        }
        return studentMapper.map(student);
    }

    @Override
    public StudentEntity getById(String id) {
        Optional<StudentEntity> opt = Optional.ofNullable(studentRepository.findByStudentIdAndSoftDeleteFalse(id));
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }
        return opt.get();
    }

    @Override
    public List<StudentEntity> getAll() {
        List<StudentEntity> lista = new ArrayList<>();
        lista = studentRepository.findAll();
        return lista;
    }

    @Override
    public StudentDto addStudentToCourse(String idStudent, String idCourse) throws Exception {
        StudentEntity student = getById(idStudent);
        CourseEntity course = courseService.getByIdAndSoftDeleteFalse(idCourse);
        if (course.getStudentsList().contains(student)) {
            throw new Exception("this student is already assigned to this course.");
        }
        student.addCourse(course);
        course.addStudent(student);
        return studentMapper.map(student);
    }

    @Override
    public StudentDto removeStudentFromCourse(String idStudent, String idCourse) throws Exception {
        StudentEntity student = getById(idStudent);
        CourseEntity course = courseService.getByIdAndSoftDeleteFalse(idCourse);
        if (!course.getStudentsList().contains(student)) {
            throw new Exception("this student does not have this course.");
        }
        student.removeCourse(course);
        course.removeStudent(student);
        return studentMapper.map(student);
    }
}
