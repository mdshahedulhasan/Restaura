package com.peytosoft.AuthService.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.peytosoft.AuthService.Model.Admin;



@FeignClient("ADMINSERVICE")
public interface CallAdmin {
	
	@PostMapping("admin/addAdmin")
    public ResponseEntity<String> addAdmin(@RequestBody Admin admin);


}
