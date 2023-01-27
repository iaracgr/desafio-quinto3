package com.gonzalez.desafioquintoimpacto.service;

import com.gonzalez.desafioquintoimpacto.dto.CourseDto;
import com.gonzalez.desafioquintoimpacto.mapper.CourseMapper;
import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import com.gonzalez.desafioquintoimpacto.repository.ICourseRepository;
import com.gonzalez.desafioquintoimpacto.service.abstraction.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseRepository courseRepository;

    @Autowired
    private CourseMapper courseMapper;

    @Override
    @Transactional
    public void createCourse(String name, String daytime,String schedule) throws Exception {
        CourseEntity course = new CourseEntity();
        if (findByNameAndSoftDeleteFalse(name) == null){
            course.setName(name);
            course.setDaytime(daytime);
            course.setSchedule(schedule);
            courseRepository.save(course);
        } else {
            throw new Exception("There is a course with this name");
        }
    }

    @Override
    public CourseDto updateCourse(String id, String name, String daytime, String schedule) throws Exception {
      Optional<CourseEntity> courseEntity = courseRepository.findById(id);
      if (courseEntity.isPresent()){
          CourseEntity newCourse = courseEntity.get();
          if (name != null){ // todo:agregar validacion de nombre
              newCourse.setName(name);
          }
          if (daytime != null){
              newCourse.setDaytime(daytime);
          }
          if (schedule != null){
              newCourse.setSchedule(schedule);
          }
          CourseDto dto = courseMapper.map(courseRepository.save(newCourse));
          return dto;
      } else {
          throw new Exception("course not found");
      }
    }

    private List<CourseDto> buildListResponse(List<CourseEntity> courses) {
        List<CourseDto> courseResponses = courseMapper.map(courses);
        return courseResponses;
    }

    @Override
    public List<CourseDto> getCourses()  {
        List<CourseEntity> list = courseRepository.findAll();
        return buildListResponse(list);
    }
    public CourseEntity findByNameAndSoftDeleteFalse(String nameCourse) {
        Optional<CourseEntity> opt = Optional.ofNullable(courseRepository.findByName(nameCourse));
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }
        return opt.get();
    }

    @Override
    public CourseDto getByNameAndSoftDeleteFalse(String name) throws EntityNotFoundException {
        return courseMapper.map(findByNameAndSoftDeleteFalse(name));
    }

    @Override
    public CourseEntity getByIdAndSoftDeleteFalse(String id) throws EntityNotFoundException {
        Optional<CourseEntity> opt = Optional.ofNullable(courseRepository.findByCourseIdAndSoftDeleteFalse(id));
        if (opt.isEmpty()) {
            throw new EntityNotFoundException("Course not found");
        }
        return opt.get();
    }

    @Override
    public void deleteCourse(String id) {
        CourseEntity course= getByIdAndSoftDeleteFalse(id);
        course.setSoftDelete(true);
        courseRepository.save(course);
    }
}
