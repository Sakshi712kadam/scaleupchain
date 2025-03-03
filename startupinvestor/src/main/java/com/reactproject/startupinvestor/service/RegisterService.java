package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.dto.RegisterDto;
import com.reactproject.startupinvestor.entities.RegisterEntity;
import com.reactproject.startupinvestor.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RegisterService {

    @Autowired
    private RegisterRepository registerRepository;

    @Autowired
    private EmailService emailService;

    public String createRegisterEntity(RegisterDto registerDto) {
        if (registerRepository.existsByEmail(registerDto.getEmail())) {
            return "Email is already registered";
        }

        // Generate OTP
        //String otp = emailService.generateOTP();

        RegisterEntity registerEntity = new RegisterEntity();
        registerEntity.setEmail(registerDto.getEmail());
        registerEntity.setPassword(registerDto.getPassword()); // Hash the password later
        registerEntity.setPhone(registerDto.getPhone());
        registerEntity.setCity(registerDto.getCity());
        registerEntity.setCountry(registerDto.getCountry());
        registerEntity.setUserType(registerDto.getUserType());
        registerEntity.setOtp(registerDto.getOpt());
        //registerEntity.setVerified(false);  // Initially set to false

        // Save user with OTP
        registerRepository.save(registerEntity);

        // Send OTP email
        //emailService.sendOTP(registerDto.getEmail());

        return "User registered successfully. Please verify your email using OTP.";
    }

    // OTP Verification
    public String verifyOTP(String email, String otp) {
        Optional<RegisterEntity> userOptional = registerRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            RegisterEntity user = userOptional.get();

            if (user.getOtp().equals(otp)) {
                user.setVerified(true);
                user.setOtp(null); // Clear OTP after verification
                registerRepository.save(user);
                return "Email verified successfully!";
            } else {
                return "Invalid OTP. Please try again.";
            }
        }
        return "User not found.";
    }

    public List<RegisterEntity> getAllRegisterEntities() {
        return registerRepository.findAll();
    }

    public Optional<RegisterEntity> getRegisterEntity(Long id) {
        return registerRepository.findById(id);
    }

    public String updateRegisterEntity(Long id, RegisterDto registerDto) {
        Optional<RegisterEntity> optionalUser = registerRepository.findById(id);
        if (optionalUser.isPresent()) {
            RegisterEntity registerEntity = optionalUser.get();
            registerEntity.setEmail(registerDto.getEmail());
            registerEntity.setPassword(registerDto.getPassword()); // Hash the new password
            registerEntity.setPhone(registerDto.getPhone());
            registerEntity.setCity(registerDto.getCity());
            registerEntity.setCountry(registerDto.getCountry());
            registerEntity.setUserType(registerDto.getUserType());

            registerRepository.save(registerEntity);
            return "User updated successfully";
        } else {
            return "User not found";
        }
    }

    public String deleteRegisterEntity(Long id) {
        Optional<RegisterEntity> user = registerRepository.findById(id);
        if (user.isPresent()) {
            registerRepository.deleteById(id);
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }

}
