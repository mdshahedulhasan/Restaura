package com.peytosoft.VendorService.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.peytosoft.VendorService.Dao.VendorDao;
import com.peytosoft.VendorService.Model.Vendor;

@Service
public class VendorService {
	
	@Autowired
	VendorDao vendorDao;
	
	private final String FOLDER_PATH="C:\\Users\\DELL\\Documents\\myFiles\\vendors\\";

	public ResponseEntity<List<Vendor>> getAllVendors() {
		try {
            return new ResponseEntity<>(vendorDao.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addVendor(Vendor vendor) {
		vendorDao.save(vendor);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<String> uploadImageToFileSystem(Integer vendorId,MultipartFile file) {
		
		String originalFileName= file.getOriginalFilename();
		
		String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = vendorId + fileExtension;
				
		String filePath=FOLDER_PATH+newFileName;
		 
		 
				Vendor existingVendor = vendorDao.findById(vendorId).orElse(null);
				existingVendor.setImageFilePath(filePath);
				vendorDao.save(existingVendor);
		

	       try {
				file.transferTo(new File(filePath));
			} 
		     catch (IOException e) {
				
		    	 return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
			}
  
	       return new ResponseEntity<>("Success",HttpStatus.CREATED);
	        
	}

	public byte[] downloadImageFromFileSystem(Integer vendorId) {
		 Optional<Vendor> vendor = vendorDao.findById(vendorId);
	        String filePath=vendor.get().getImageFilePath();
	       
			try {
				byte[]	images = Files.readAllBytes(new File(filePath).toPath());
				return images;
				
			} catch (IOException e) {
				return null;
			}
	       
	}

}
