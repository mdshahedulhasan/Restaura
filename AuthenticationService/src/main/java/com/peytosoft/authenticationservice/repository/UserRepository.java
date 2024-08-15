package com.peytosoft.authenticationservice.repository;


import com.peytosoft.authenticationservice.model.RestouraUser;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepository extends JpaRepository<RestouraUser, Long> {

    RestouraUser findByUseremail(String useremail);
}
