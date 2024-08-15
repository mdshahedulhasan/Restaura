package com.peytosoft.authenticationservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String useremail;
    private String password;
    private List<String> authorities;
}
