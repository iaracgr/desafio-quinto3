package com.gonzalez.desafioquintoimpacto.dto;


import com.gonzalez.desafioquintoimpacto.model.entity.ProfessorEntity;
import com.gonzalez.desafioquintoimpacto.model.entity.StudentEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

    @NotBlank(message = "Course Name cannot be null or empty.")
    private String name;

    private String id;

    private String daytime;

    private String schedule;

    private List<StudentEntity> studentsList;

    private List<ProfessorEntity> professorList;



}

