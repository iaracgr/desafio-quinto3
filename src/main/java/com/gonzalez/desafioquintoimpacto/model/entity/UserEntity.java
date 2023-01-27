package com.gonzalez.desafioquintoimpacto.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="users")
public class UserEntity { // is for Admin creation

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "user_id")
    private   String userId;

    @Column(name = "first_name",nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    protected List<RoleEntity> roles;

    @Column(name = "soft_delete",nullable = false)
    private Boolean softDelete=false;


    public boolean isEnabled() { return !softDelete; }

}

