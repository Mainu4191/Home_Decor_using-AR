package com.example.arproject1.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Model.AdminSignupRequestModel;
import com.example.arproject1.Services.AdminAuthServices;

@RestController
@RequestMapping("/admin/signup")
public class AdminSignupControllers {

    private final AdminAuthServices adminAuthServices;

    public AdminSignupControllers(AdminAuthServices adminAuthServices) {
        this.adminAuthServices = adminAuthServices;
        System.out.println("AdminSignupControllers");
    }

    @GetMapping()
    public ResponseEntity<?> signupAdmin() {

        String Email = "admin@gmail.com";
        String Password = "123";
        String name = "Admin";
        AdminSignupRequestModel adminSignupRequestModel2 = new AdminSignupRequestModel();
        adminSignupRequestModel2.setEmail(Email);
        adminSignupRequestModel2.setPassword(Password);
        adminSignupRequestModel2.setName(name);

        boolean isSignupSuccess = adminAuthServices.createAdmin(adminSignupRequestModel2);
        if (isSignupSuccess) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Admin Created Successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create admin");
        }

        
    }

}
