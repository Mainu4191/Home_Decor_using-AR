package com.example.arproject1.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Services.UsersServices;
import com.example.arproject1.utils.Response;

@SuppressWarnings("unused")
@RestController
@RequestMapping("/users/signup")
public class UsersSignupControllers {

    private final UsersServices usersServices;

    public UsersSignupControllers(UsersServices usersServices) {
        this.usersServices = usersServices;
        System.out.println("UsersSignupControllers");
    }

    
    @PostMapping()
    public Response signupUsers(@RequestBody UsersEntity usersEntity) {

        String name = usersEntity.getName();
        String email = usersEntity.getEmail();
        String password = usersEntity.getPassword();
        String phone = usersEntity.getPhone();
        String address = usersEntity.getAddress();

        

        boolean isUsersCreated = usersServices.createUser(name, email, password, address, phone);

        if (isUsersCreated) {
            return new Response("Users Created Successfully", true);
        } else {
            return new Response("Failed to create Users", false);
        }

    }

}
