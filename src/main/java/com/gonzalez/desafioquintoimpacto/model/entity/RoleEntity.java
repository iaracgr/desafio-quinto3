package com.gonzalez.desafioquintoimpacto.model.entity;

import com.gonzalez.desafioquintoimpacto.config.RoleType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class RoleEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ROLE_ID", nullable = false)
    private String roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, unique = true)
    private RoleType name;

}

