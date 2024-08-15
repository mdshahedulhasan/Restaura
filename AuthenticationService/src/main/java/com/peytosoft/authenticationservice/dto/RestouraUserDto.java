package com.peytosoft.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestouraUserDto {
    private String useremail;
    private String password;
    private List<String> authorities;
}
