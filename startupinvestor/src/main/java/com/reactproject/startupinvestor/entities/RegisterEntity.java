package com.reactproject.startupinvestor.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "registerentity")
@Getter
@Setter
public class RegisterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String userType;

    // OTP and Verification Fields
    @Column(nullable = true)
    private String otp;

    @Column(nullable = true)
    private LocalDateTime otpGeneratedTime;

    @Column(nullable = false)
    private boolean isVerified = false; // Default: Not Verified
}
