package com.peytosoft.VendorService.Controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.peytosoft.VendorService.Model.Vendor;
import com.peytosoft.VendorService.Service.VendorService;

@RestController
@RequestMapping("vendor")
public class VendorController {
	
	@Autowired
	VendorService vendorService;
	
	@GetMapping("allVendors")
    public ResponseEntity<List<Vendor>> getAllVendors(){
        return vendorService.getAllVendors();
    }
	
	@PostMapping("addVendor")
    public ResponseEntity<String> addVendor(@RequestBody Vendor vendor){
       return vendorService.addVendor(vendor);
    }
	
	@PostMapping("/uploadImage/{vendorId}")
	public ResponseEntity<String> uploadImageToFIleSystem(@PathVariable Integer vendorId, @RequestParam("image")MultipartFile file) throws IOException {
		return vendorService.uploadImageToFileSystem(vendorId,file);
		
	}
	
	@GetMapping("/downloadImage/{vendorId}")
	public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable Integer vendorId) throws IOException {
		byte[] imageData=vendorService.downloadImageFromFileSystem(vendorId);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.valueOf("image/png"))
				.body(imageData);

	}

	

}
