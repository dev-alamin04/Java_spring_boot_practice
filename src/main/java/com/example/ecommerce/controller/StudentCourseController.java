package com.example.ecommerce.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.StudentCourse;
import com.example.ecommerce.response.ApiResponse;
import com.example.ecommerce.service.CourseAssignService;

@RestController
@RequestMapping("/student-course")
public class StudentCourseController extends BaseController {
    private final CourseAssignService service;

    public StudentCourseController(CourseAssignService service){
        this.service = service;
    }

    @PostMapping
    public ApiResponse<StudentCourse> create(StudentCourse course){
        var studentCourse = service.create(course);
        return success(studentCourse,"Assign course successfully");
    }

}
