package com.gonzalez.desafioquintoimpacto.repository;


import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProfessorRepository extends JpaRepository <ProfessorEntity,String >{

    ProfessorEntity findByProfessorIdAndSoftDeleteFalse (String id);

    ProfessorEntity findByName (String name);

    ProfessorEntity findByEmailAndSoftDeleteFalse (String email);

    ProfessorEntity findByEmail (String email);


    List<ProfessorEntity> findAll();
}

