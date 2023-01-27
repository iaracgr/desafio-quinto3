package com.gonzalez.desafioquintoimpacto.repository;


import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity,String > {

    StudentEntity findByStudentIdAndSoftDeleteFalse (String id);

    StudentEntity findByEmailAndSoftDeleteFalse (String email);

    StudentEntity findByName(String name);

    List<StudentEntity> findAll ();
}

