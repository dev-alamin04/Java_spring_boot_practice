package com.example.ecommerce.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.StudentCourse;
import com.example.ecommerce.repository.StudentCourseRepository;

@Service
public class CourseAssignService {
    private final StudentCourseRepository repository;

    public CourseAssignService(StudentCourseRepository repository) {
        this.repository = repository;
    }

    public List<StudentCourse> getAll() {
        return repository.findAll();
    }

    public StudentCourse create(StudentCourse studentCourse) {
        return repository.save(studentCourse);
    }

    public void destory(Long id) {
        StudentCourse studentCourse = repository.findById(id).orElseThrow(() -> new RuntimeException("Remove course "));
        repository.delete(studentCourse);
    }
}
