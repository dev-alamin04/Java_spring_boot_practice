package com.example.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
