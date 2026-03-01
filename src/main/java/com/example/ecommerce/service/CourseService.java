package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Course;
import com.example.ecommerce.repository.CourseRepository;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository){
        this.repository = repository;
    }

    public List<Course>getAll(){
        return repository.findAll();
    }

    public Course create(Course course){
        course.setCourse_code((long)(Math.random() * (9999999 - 11111 + 1)) + 11111);
        return repository.save(course);
    }

    public Course courseInformation(Long id){
        Course course = repository.findById(id).orElseThrow(()->new RuntimeException("Course Details not found by id"));
        return course;
    }

    public Course courseUpdate(Long id, Course courseDetails){
        Course course = repository.findById(id).orElseThrow(()->new RuntimeException("Course not found"));
        course.setTitle(courseDetails.getTitle());
        course.setClass_from_time(courseDetails.getClass_from_time());
        course.setClass_to_time(courseDetails.getClass_to_time());
        course.setCourse_duration(courseDetails.getCourse_duration());

        return repository.save(course);

    }



    
}
