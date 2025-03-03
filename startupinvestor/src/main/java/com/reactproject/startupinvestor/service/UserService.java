package com.reactproject.startupinvestor.service;

import com.reactproject.startupinvestor.entities.RegisterEntity;
import com.reactproject.startupinvestor.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private RegisterRepository registerRepository;

    public boolean validateUser(String email, String password) {
        Optional<RegisterEntity> user = registerRepository.findByEmail(email);
        return user.isPresent() && user.get().getPassword().equals(password);
    }
}
