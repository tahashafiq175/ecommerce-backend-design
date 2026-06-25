package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Dto.LogInDto;
import com.Internship.ecommerce_backend_design.Dto.SignUpDto;
import com.Internship.ecommerce_backend_design.Entity.User;
import com.Internship.ecommerce_backend_design.Service.UserService;
import com.Internship.ecommerce_backend_design.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Public")
public class PublicController {
//    @AccessType(AccessType.Type.PROPERTY)
    @Autowired
UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils  jwtUtils;
    public PublicController(){
        System.out.println("PUBLIC CONTROLLER CREATED");
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpDto user){
        User newUser=new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setPassword(user.getPassword());
        userService.saveUser(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED) ;
    }
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LogInDto user){
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        String token = jwtUtils.generateToken(user.getUserName());
        return new ResponseEntity<>(token,HttpStatus.OK);
    }
}
