package com.example.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.OtpVerification;

@Repository
public interface OtpRepository extends JpaRepository<OtpVerification, Long> {

    // সবচেয়ে latest OTP খুঁজে বের করবে
    Optional<OtpVerification> findTopByEmailOrderByIdDesc(String email);
}