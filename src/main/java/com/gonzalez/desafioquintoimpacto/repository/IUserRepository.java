package com.gonzalez.desafioquintoimpacto.repository;


import com.gonzalez.desafioquintoimpacto.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity,String> {

    UserEntity findByEmailAndSoftDeleteFalse(String email);

    UserEntity findByEmail(String email);

    UserEntity findByUserIdAndSoftDeleteFalse(String id);

    List<UserEntity> findAll();
}


