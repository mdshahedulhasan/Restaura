package com.peytosoft.authenticationservice.controller;

import com.peytosoft.authenticationservice.model.*;
import com.peytosoft.authenticationservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<RestouraUser> signUp(@RequestBody SignUpRequest signUpRequest){
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> signIn(@RequestBody LogInRequest signInRequest){
        return authService.login(signInRequest);
    }
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody String previousToken){
        return authService.refreshToken(previousToken);
    }



}
