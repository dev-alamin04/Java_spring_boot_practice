package com.example.ecommerce.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    // OTP email পাঠাও — Laravel এর Mail::to()->send() এর মতো
    public void sendOtpEmail(String toEmail, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Email Verification OTP");
        message.setText("Your OTP is: " + otp + "\n\nThis OTP will expire in 5 minutes.");
        mailSender.send(message);
    }
}