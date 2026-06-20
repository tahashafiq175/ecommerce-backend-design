package com.Internship.ecommerce_backend_design.Service;

import com.Internship.ecommerce_backend_design.Entity.User;
import com.Internship.ecommerce_backend_design.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    private  static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
private UserRepository userRepository;
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
}
