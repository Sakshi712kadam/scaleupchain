package com.reactproject.startupinvestor.controller;

import com.reactproject.startupinvestor.dto.RegisterDto;
import com.reactproject.startupinvestor.entities.RegisterEntity;
import com.reactproject.startupinvestor.service.EmailService;
import com.reactproject.startupinvestor.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/register")
@CrossOrigin(origins = "http://localhost:3000")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @Autowired
    private EmailService emailService;

    // 🔹 STEP 1: Send OTP to email before registration
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOTP(@RequestParam String email) {
        emailService.sendOTP(email);
        return new ResponseEntity<>("OTP sent successfully to " + email, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String otp = request.get("otp");

        String result = emailService.verifyOTP(email, otp);

        if (!result.equals("OTP verified successfully!")) {
            return ResponseEntity.badRequest().body(result);
        }

        return ResponseEntity.ok(result);
    }

    // 🔹 STEP 2: User enters OTP + details → Register if OTP is correct
    @PostMapping
    public ResponseEntity<String> createRegisterEntity(@RequestBody RegisterDto registerDto) {
        String isVerified = emailService.verifyOTP(registerDto.getEmail(), registerDto.getOpt()); // ✅ Fix typo

        if (isVerified.equals("false")) {
            return new ResponseEntity<>("Invalid or expired OTP! Please try again.", HttpStatus.BAD_REQUEST);
        }

        String response = registerService.createRegisterEntity(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 🔹 Get all registered users
    @GetMapping
    public ResponseEntity<List<RegisterEntity>> getAllRegisterEntities() {
        List<RegisterEntity> registerEntities = registerService.getAllRegisterEntities();
        return new ResponseEntity<>(registerEntities, HttpStatus.OK);
    }

    // 🔹 Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<RegisterEntity> getRegisterEntityById(@PathVariable Long id) {
        Optional<RegisterEntity> registerEntity = registerService.getRegisterEntity(id);
        return registerEntity.map(entity -> new ResponseEntity<>(entity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // 🔹 Update a user by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRegisterEntity(@PathVariable Long id, @RequestBody RegisterDto registerDto) {
        String response = registerService.updateRegisterEntity(id, registerDto);
        return response.equals("User updated successfully")
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // 🔹 Delete a user by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegisterEntity(@PathVariable Long id) {
        String response = registerService.deleteRegisterEntity(id);
        return response.equals("User deleted successfully")
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
