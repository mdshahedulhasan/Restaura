package com.peytosoft.AuthService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.peytosoft.AuthService.Model.Patient;




@FeignClient("PATIENTSERVICE")
public interface CallPatient {
	
	@PostMapping("patient/addPatient")
    public ResponseEntity<String> createPatient(@RequestBody Patient patient);

}
