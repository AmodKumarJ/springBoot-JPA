package com.amod.jpa.Controller;

import com.amod.jpa.Model.User;
import com.amod.jpa.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService service;

    @PostMapping("api/user/save")
    public void save(User user){
        service.save(user);
        System.out.println("User saved successfully");
    }
    @PostMapping("api/user/detail/{id}}")
    public Optional<User> userDetail(@PathVariable int id){
        return service.getUserById(id);
    }
}
