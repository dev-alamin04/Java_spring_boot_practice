package com.example.ecommerce.controller;

import com.example.ecommerce.service.CourseService;

public class CourseController {
    
    private final CourseService service;
    public CourseController(CourseService service){
        this.service = service;
    }
}
