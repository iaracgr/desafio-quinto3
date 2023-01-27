package com.gonzalez.desafioquintoimpacto.service.abstraction;

import com.gonzalez.desafioquintoimpacto.dto.CourseDto;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface ICourseService {

    void createCourse(String name, String daytime, String schedule) throws Exception;

    CourseDto updateCourse(String id,String name, String daytime, String schedule) throws Exception;

    List<CourseDto> getCourses() throws EntityNotFoundException;

    CourseDto getByNameAndSoftDeleteFalse(String name) throws EntityNotFoundException;

    CourseEntity getByIdAndSoftDeleteFalse(String id) throws EntityNotFoundException;

    void deleteCourse(String id);
}
