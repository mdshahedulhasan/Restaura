package com.peytosoft.VendorService.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.peytosoft.VendorService.Model.Vendor;

@Repository
public interface VendorDao extends JpaRepository<Vendor,Integer>{

}
