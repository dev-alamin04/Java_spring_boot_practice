package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.entity.Student;
import com.example.ecommerce.service.StudentService;
import com.example.ecommerce.response.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/students")
public class StudentController extends BaseController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    // Get all students
    @GetMapping
    public ApiResponse<List<Student>> getAll() {
        var students = service.getAll();
        return success(students, "All students fetched");
    }

    // Get single student information
    @GetMapping("/{id}")
    public ApiResponse<Student> show(@PathVariable Long id) {
        var student = service.studentInformation(id);
        return success(student, "Student get Successfully");
    }

    // Create student
    @PostMapping
    public ApiResponse<Student> create(@Valid @RequestBody Student student) {
        var saved = service.create(student);
        return success(saved, "Student created successfully");
    }

    // Update student
    @PutMapping("/{id}")
    public ApiResponse<Student> update(@PathVariable Long id, @Valid @RequestBody Student student) {
        var updated = service.updateStudent(id, student);
        return success(updated, "Student updated successfully");
    }

    // Delete student
    @DeleteMapping("/{id}")
    public ApiResponse<String> destroy(@PathVariable Long id) {
        service.deleteStudent(id);
        return success(null, "Student deleted successfully");
    }
}