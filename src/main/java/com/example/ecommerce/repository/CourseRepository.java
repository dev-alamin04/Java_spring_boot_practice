package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Course;

public interface CourseRepository extends JpaRepository<Course,Long> {}
