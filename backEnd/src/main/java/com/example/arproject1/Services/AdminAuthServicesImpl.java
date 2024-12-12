package com.example.arproject1.Services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.AdminEntity;
import com.example.arproject1.Model.AdminSignupRequestModel;
import com.example.arproject1.Repository.AdminRepository;

@Service
public class AdminAuthServicesImpl implements AdminAuthServices {


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthServicesImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createAdmin(AdminSignupRequestModel adminSignupRequestModel) {

        if(adminRepository.existsByEmail(adminSignupRequestModel.getEmail())){
            return false;
        }

        AdminEntity admin = new AdminEntity();
        BeanUtils.copyProperties(adminSignupRequestModel, admin);
        String hashedPassword = passwordEncoder.encode(adminSignupRequestModel.getPassword());
        admin.setPassword(hashedPassword);
        
        adminRepository.save(admin);

        return true;
    }
    
}
