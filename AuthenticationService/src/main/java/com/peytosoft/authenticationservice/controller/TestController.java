package com.peytosoft.authenticationservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping("/")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("Hello World, this is test");
    }

    @GetMapping("/public")
    public ResponseEntity<String> testpublic(){
        return ResponseEntity.ok("Hello World, this is testpublic");
    }
}
