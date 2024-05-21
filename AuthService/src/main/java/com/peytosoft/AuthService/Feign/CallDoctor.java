package com.peytosoft.AuthService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.peytosoft.AuthService.Model.Doctor;



@FeignClient("DOCTORSERVICE")
public interface CallDoctor {
	
	@PostMapping("doctor/addDoctor")
    public ResponseEntity<String> addDoctor(@RequestBody Doctor doctor);

}
