package com.example.ecommerce.service;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.OtpVerification;
import com.example.ecommerce.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final EmailService emailService;

    // OTP generate করো এবং email পাঠাও
    public void generateAndSendOtp(String email) {

        // 6 digit OTP বানাও
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);

        // Database এ save করো
        OtpVerification otpVerification = new OtpVerification();
        otpVerification.setEmail(email);
        otpVerification.setOtp(otp);
        otpVerification.setExpiresAt(LocalDateTime.now().plusMinutes(5));
        otpVerification.setUsed(false);
        otpRepository.save(otpVerification);

        // Email পাঠাও
        emailService.sendOtpEmail(email, otp);
    }

    // OTP verify করো
    public boolean verifyOtp(String email, String otp) {

        OtpVerification otpVerification = otpRepository
                .findTopByEmailOrderByIdDesc(email)
                .orElse(null);

        // OTP নেই
        if (otpVerification == null) return false;

        // Already use হয়েছে
        if (otpVerification.isUsed()) return false;

        // Expire হয়ে গেছে
        if (otpVerification.getExpiresAt().isBefore(LocalDateTime.now())) return false;

        // OTP match করে না
        if (!otpVerification.getOtp().equals(otp)) return false;

        // সব ঠিক আছে — used mark করো
        otpVerification.setUsed(true);
        otpRepository.save(otpVerification);

        return true;
    }
}