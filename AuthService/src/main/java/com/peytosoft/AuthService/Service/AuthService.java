package com.peytosoft.AuthService.Service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.peytosoft.AuthService.Dao.UserRepository;
import com.peytosoft.AuthService.Feign.CallAdmin;
import com.peytosoft.AuthService.Feign.CallDoctor;
import com.peytosoft.AuthService.Feign.CallPatient;
import com.peytosoft.AuthService.Model.Admin;
import com.peytosoft.AuthService.Model.Doctor;
import com.peytosoft.AuthService.Model.Patient;
import com.peytosoft.AuthService.Model.ReqRes;
import com.peytosoft.AuthService.Model.User;
import com.peytosoft.AuthService.Util.JWTUtils;


@Service
public class AuthService {
	
	@Autowired
    private UserRepository ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CallPatient callPatient;
    @Autowired
    private CallDoctor callDoctor;
    @Autowired
    private CallAdmin callAdmin;

    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
       
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(registrationRequest.getRole());
            User ourUserResult = ourUserRepo.save(user);
            if (ourUserResult != null && ourUserResult.getId()>0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
            
            if (user.getRole().equals("PATIENT")) {
            	Patient patient = new Patient(user.getId(),user.getEmail());
            	String x = callPatient.createPatient(patient).getBody();
            	System.out.println("Patient creation status "+x);
            }
            
            else if (user.getRole().equals("DOCTOR")) {
            	Doctor doctor = new Doctor(user.getId(),user.getEmail());
            	String x = callDoctor.addDoctor(doctor).getBody();
            	System.out.println("Doctor creation status "+x);
            }
            
            else if (user.getRole().equals("Admin")){
            	Admin admin = new Admin(user.getId(),user.getEmail());
            	String x = callAdmin.addAdmin(admin).getBody();
            	System.out.println("Admin creation status "+x);
            }
            
            }catch (Exception e){
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
            }
            return resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));
            var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();
            System.out.println("USER IS: "+ user);
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Signed In");
        }catch (Exception e){
            response.setStatusCode(500);
            response.setError(e.getMessage());
        }
        return response;
    }

    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        ReqRes response = new ReqRes();
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        User users = ourUserRepo.findByEmail(ourEmail).orElseThrow();
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
            var jwt = jwtUtils.generateToken(users);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}
