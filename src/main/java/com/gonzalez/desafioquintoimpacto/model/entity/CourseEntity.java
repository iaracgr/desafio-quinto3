package com.gonzalez.desafioquintoimpacto.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "course_id")
    private String courseId;

    @Column(name = "course_name",nullable = false,unique = true)
    private String name;

    @Column(name = "daytime") // turno: mañana,tarde,noche
    private String daytime;

    @Column(name = "schedule") // horario
    private String schedule;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.PERSIST},
            fetch = FetchType.LAZY )
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<StudentEntity> studentsList = new ArrayList<StudentEntity>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.PERSIST},
            fetch = FetchType.LAZY )
    @JoinTable(
            name = "professor_courses",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<ProfessorEntity> professorsList =new ArrayList<ProfessorEntity>();

    @Column(name = "soft_delete",nullable = false)
    private Boolean softDelete=false;

    public boolean isEnabled() { return !softDelete; }

    public void addStudent(StudentEntity student) {
        studentsList.add(student);
    }

    public void addProfessor(ProfessorEntity professor) {
        professorsList.add(professor);
    }

    public void removeStudent(StudentEntity student) {
        studentsList.remove(student);
    }

    public void removeProfessor(ProfessorEntity professor) {
        professorsList.remove(professor);
    }

}

