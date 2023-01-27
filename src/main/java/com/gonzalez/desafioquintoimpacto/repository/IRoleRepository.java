package com.gonzalez.desafioquintoimpacto.repository;

import com.gonzalez.desafioquintoimpacto.config.RoleType;
import com.gonzalez.desafioquintoimpacto.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<RoleEntity,String> {

    RoleEntity findByName(RoleType name);

}

