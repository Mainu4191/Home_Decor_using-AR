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
@RequestMapping("/users/login")
public class UsersLoginControllers {

    private final UsersServices usersServices;

    public UsersLoginControllers(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @PostMapping()
    public Response loginUsers(@RequestBody UsersEntity usersEntity) {

        String email = usersEntity.getEmail();
        String password = usersEntity.getPassword();

        Response getUsersLoginInfo = usersServices.userLogin(email, password);
        boolean isUsersLogin = getUsersLoginInfo.isStatus();
        

        if (isUsersLogin) {
           
            return getUsersLoginInfo;
        } else {
           
            return getUsersLoginInfo;
        }

    }

}
