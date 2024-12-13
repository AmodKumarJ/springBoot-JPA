package com.amod.jpa.Services;

import com.amod.jpa.Model.Tasks;
import com.amod.jpa.Model.User;
import com.amod.jpa.Repository.TaskRepo;
import com.amod.jpa.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    TaskRepo taskRepo;
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
        userRepo.updateUser(id,user.getName(),user.getPassword(),user.getEmail());
    }
    public void deleteUser(int id){
        userRepo.deleteById(id);
    }
    public List<User>getUserByEmailDomain(String domain){
        return userRepo.findByEmailEndsWith(domain);
    }
    @Transactional
    public void addUserWithTask(User user) {
        userRepo.save(user);
        for(Tasks task : user.getTasks()){
            task.setUser(user);
            taskRepo.save(task);
        }
    }
    @Transactional
    public void CreateTask(Tasks task){
        taskRepo.save(task);
    }


}
