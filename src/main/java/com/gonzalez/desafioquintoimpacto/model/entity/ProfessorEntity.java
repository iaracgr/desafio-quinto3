package com.gonzalez.desafioquintoimpacto.model.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "professor_id")
    private   String professorId;

    @Column(name = "first_name",nullable = false)
    private String name;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "soft_delete",nullable = false)
    private Boolean softDelete=false;

    @Column(name = "surname", nullable = false)
    private String surname;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    protected List<RoleEntity> roles;

    @ManyToMany(mappedBy = "studentsList")
    private List<CourseEntity> courses = new ArrayList<>();

    @Column(name = "course_id")
    private String courseId;
    public boolean isEnabled() { return !softDelete; }

    public void addCourse(CourseEntity course){courses.add(course);}

    public void removeCourse(CourseEntity course){courses.remove(course);}


}
