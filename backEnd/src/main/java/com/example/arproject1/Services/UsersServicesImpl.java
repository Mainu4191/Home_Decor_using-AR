package com.example.arproject1.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.arproject1.Entities.UsersEntity;
import com.example.arproject1.Repository.UsersRepository;
import com.example.arproject1.utils.Response;

@Service
public class UsersServicesImpl implements UsersServices {

    private final UsersRepository usersRepository;

    public UsersServicesImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean createUser(String name, String email, String password, String address, String phone) {

        UsersEntity user = new UsersEntity();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);

        usersRepository.save(user);

        return true;
    }

    @Override
    public boolean updateUser(long id, String name, String email, String password, String address, String phone) {

        UsersEntity user = usersRepository.findById(id).get();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAddress(address);
        user.setPhone(phone);

        usersRepository.save(user);

        return true;

    }

    @SuppressWarnings("null")
    @Override
    public boolean deleteUser(long id) {

        UsersEntity user = usersRepository.findById(id).get();

        usersRepository.delete(user);

        return true;
    }

    @Override
    public List<UsersEntity> getAllUser() {

        return usersRepository.findAll();

    }

    @Override
    public UsersEntity getUserById(long id) {

        UsersEntity user = usersRepository.findById(id).get();

        if (user != null) {
            return user;
        }

        return null;
    }

    @Override
    public UsersEntity getUserByEmail(String email) {

        return usersRepository.findByEmail(email);
    }

    @Override
    public Response userLogin(String email, String password) {

        @SuppressWarnings("unused")
        List<Response> res = new ArrayList<>();

        UsersEntity user = usersRepository.findByEmail(email);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                String userId = String.valueOf(user.getId());
                Response r = new Response("Users Login Successfully", true, userId);
                return r;
            }
        }

        Response r = new Response("Failed to Login Users", false);

        return r;
    }
   
}
