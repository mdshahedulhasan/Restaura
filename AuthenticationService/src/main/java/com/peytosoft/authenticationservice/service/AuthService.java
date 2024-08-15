package com.peytosoft.authenticationservice.service;

import com.peytosoft.authenticationservice.model.*;
import com.peytosoft.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    JWTService jwtService;


    public RestouraUser signUp(SignUpRequest signUpRequest) {
        RestouraUser user = new RestouraUser();
        user.setUseremail(signUpRequest.getUseremail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setAuthorities(signUpRequest.getAuthorities());
        user =  userRepository.save(user);
        user.setPassword("");
        return user;
    }

    public ResponseEntity<LoginResponse> login(LogInRequest loginRequest) {

        try{
            UserDetails authenticatedUser = authenticate(loginRequest);
            String token = jwtService.generateToken(authenticatedUser);
            LoginResponse loginResponse =  new LoginResponse(token, jwtService.getJwtExpiration());
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        }
        catch(AuthenticationException e)
        {
            LoginResponse loginResponse = new LoginResponse();
            return new ResponseEntity<>(loginResponse, HttpStatus.UNAUTHORIZED);
        }


    }

    public ResponseEntity<String> refreshToken(String refreshTokenRequest) {
        String useremail = jwtService.extractUsername(refreshTokenRequest);
        UserDetails user = customUserDetailsService.loadUserByUsername(useremail);

        if(!jwtService.isTokenExpired(refreshTokenRequest)){

            String token = jwtService.generateToken(user);
            return ResponseEntity.ok(token);
        }
        return new ResponseEntity<>("Invalid Token", HttpStatus.UNAUTHORIZED);

    }
    public UserDetails authenticate(LogInRequest input) throws AuthenticationException {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        return customUserDetailsService.loadUserByUsername(input.getUsername());
    }
}
