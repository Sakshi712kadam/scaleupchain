package com.reactproject.startupinvestor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> otpStorage = new HashMap<>(); // Store OTPs
    private Map<String, Long> otpExpiry = new HashMap<>(); // Store expiration time

    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

    // Generate a 6-digit OTP
    public String generateOTP() {
        return String.valueOf(100000 + new Random().nextInt(900000));
    }

    // Send OTP Email & Store OTP
    public void sendOTP(String toEmail) {
        String otp = generateOTP();
        otpStorage.put(toEmail, otp);
        otpExpiry.put(toEmail, System.currentTimeMillis() + OTP_VALID_DURATION); // Set expiry time

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Your OTP for Email Verification");
        message.setText("Your OTP for verification is: " + otp + "\n\nValid for 5 minutes.");
        mailSender.send(message);
    }

    // Verify OTP
    public String verifyOTP(String email, String otp) {
        if (!otpStorage.containsKey(email)) {
            return "No OTP found. Please request a new one.";
        }

        if (System.currentTimeMillis() > otpExpiry.get(email)) {
            otpStorage.remove(email);
            otpExpiry.remove(email);
            return "OTP has expired. Please request a new one.";
        }

        if (!otpStorage.get(email).equals(otp)) {
            return "Invalid OTP. Please enter the correct one.";
        }

        // OTP is correct, remove it after verification
        otpStorage.remove(email);
        otpExpiry.remove(email);

        return "OTP verified successfully!";
    }

}
