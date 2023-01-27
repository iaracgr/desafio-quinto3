package com.gonzalez.desafioquintoimpacto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String idStudent;

    @NotBlank(message = "First name cannot be empty or null.")
    private String name;

    @NotBlank(message = "Email cannot be empty or null.")
    @Email(message = "Email is not valid.")
    private String email;

    private String age;

    private Date birthday;

    private String history;

    private String idCourse;

    private List<String> courses;

    private Boolean softDelete;
}
