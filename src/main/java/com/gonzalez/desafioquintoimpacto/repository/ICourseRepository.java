package com.gonzalez.desafioquintoimpacto.repository;


import com.gonzalez.desafioquintoimpacto.model.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<CourseEntity,String> {

    CourseEntity findByCourseIdAndSoftDeleteFalse(String id);

    CourseEntity findByCourseId(String id);

    CourseEntity findByName(String name);

    List<CourseEntity> findAll ();


}

