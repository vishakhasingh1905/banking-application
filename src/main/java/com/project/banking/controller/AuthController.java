package com.project.banking.controller;

import com.project.banking.dto.SignInRequest;
import com.project.banking.dto.SignUpRequest;
import com.project.banking.service.SignInService;
import com.project.banking.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    @Autowired
    private SignUpService signUpService;
    @Autowired
    private SignInService signInService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest signUpRequest) {
        try {
            signUpService.signUp(signUpRequest);
            return ResponseEntity.ok("Sign-up successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest signInRequest) {
        try {
            signInService.signIn(signInRequest);
            return ResponseEntity.ok("Sign-in successful!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
