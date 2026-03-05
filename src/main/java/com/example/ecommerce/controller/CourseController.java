package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.entity.Course;
import com.example.ecommerce.response.ApiResponse;
import com.example.ecommerce.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {

    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<Course>> getAll() {
        var courses = service.getAll();
        return success(courses, "all course fetched");
    }

    @PostMapping
    public ApiResponse<Course> store(@RequestBody @Valid Course course) {
        var createdCourse = service.create(course);
        return success(createdCourse, "create successfully");
    }

    @GetMapping("/{id}")
    public ApiResponse<Course> show(@PathVariable Long id) {
        var course = service.courseInformation(id);
        return success(course, "course information get successfully");
    }

    @PostMapping("/{id}")
    public ApiResponse<Course> update(@PathVariable Long id, @Valid @RequestBody Course course) {
        var updateCourse = service.courseUpdate(id, course);
        return success(updateCourse, "Course update successffuly ");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Course> destory(@PathVariable Long id) {
        service.deleteCourse(id);
        return success(null, "Delete succfully");
    }

}
