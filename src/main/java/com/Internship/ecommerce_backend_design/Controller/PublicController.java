package com.Internship.ecommerce_backend_design.Controller;

import com.Internship.ecommerce_backend_design.Entity.User;
import com.Internship.ecommerce_backend_design.Service.UserService;
import com.Internship.ecommerce_backend_design.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
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
    public String signup(@RequestBody User user){
        System.out.println("signup");
        userService.saveUser(user);
        return "taha is awesome";
    }
    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user){
        authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
        String s = jwtUtils.generateToken(user.getUserName());
        return ResponseEntity.ok(s);
    }
}
