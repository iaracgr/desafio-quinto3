package com.gonzalez.desafioquintoimpacto.controller;

import com.gonzalez.desafioquintoimpacto.dto.CourseDto;
import com.gonzalez.desafioquintoimpacto.service.abstraction.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private ICourseService iCourseService;

    @PostMapping("/create")
    public String create (@Valid @RequestParam("name") String name,
                          @RequestParam("daytime") String daytime,
                          @RequestParam("schedule") String schedule) throws Exception {
        iCourseService.createCourse(name,daytime,schedule);
return "courses.html";
    }

    @GetMapping("/list")
    public String listCourses (){
        List<CourseDto> list = iCourseService.getCourses();
        return "course";
    }
}
