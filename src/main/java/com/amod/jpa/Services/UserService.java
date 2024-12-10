package com.amod.jpa.Services;

import com.amod.jpa.Model.User;
import com.amod.jpa.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    public void save(User user){
        userRepo.save(user);
    }
    public Optional<User> getUserById(int id){
        return userRepo.findById(id);
    }
    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public void updateUser(int id,User user){
        userRepo.updateUser(id,user.getName(),user.getPassword());
    }
    public void deleteUser(int id){
        userRepo.deleteById(id);
    }
}
