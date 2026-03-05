package com.example.ecommerce.service;

import com.example.ecommerce.dto.*;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final OtpService otpService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    // ── Register ──────────────────────────────────
    public String register(RegisterRequest request) {

        // Email already আছে কিনা check করো
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // User save করো — Laravel এর User::create() এর মতো
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setVerified(false);
        userRepository.save(user);

        // OTP পাঠাও
        otpService.generateAndSendOtp(request.getEmail());

        return "Registration successful! Please check your email for OTP.";
    }

    // ── Verify OTP ────────────────────────────────
    public String verifyOtp(VerifyOtpRequest request) {

        // OTP সঠিক কিনা check করো
        boolean isValid = otpService.verifyOtp(request.getEmail(), request.getOtp());
        if (!isValid) {
            throw new RuntimeException("Invalid or expired OTP");
        }

        // User কে verified mark করো
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setVerified(true);
        userRepository.save(user);

        return "Email verified successfully!";
    }

    // ── Login ─────────────────────────────────────
    public AuthResponse login(LoginRequest request) {

        // User আছে কিনা check করো
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Email verify হয়েছে কিনা check করো
        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email first");
        }

        // Password check করো — Laravel এর Hash::check() এর মতো
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Token বানাও
        String accessToken = jwtUtil.generateAccessToken(user.getEmail());
        String refreshToken = jwtUtil.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken, "Login successful!");
    }

    // ── Refresh Token ─────────────────────────────
    public AuthResponse refreshToken(String refreshToken) {

        // Token valid কিনা check করো
        if (!jwtUtil.isTokenValid(refreshToken)) {
            throw new RuntimeException("Invalid or expired refresh token");
        }

        // Token থেকে email বের করো
        String email = jwtUtil.extractEmail(refreshToken);

        // নতুন access token দাও
        String newAccessToken = jwtUtil.generateAccessToken(email);

        return new AuthResponse(newAccessToken, refreshToken, "Token refreshed!");
    }
}