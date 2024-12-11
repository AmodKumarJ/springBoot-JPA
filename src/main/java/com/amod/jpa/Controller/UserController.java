package com.amod.jpa.Controller;

import com.amod.jpa.Model.Tasks;
import com.amod.jpa.Model.User;
import com.amod.jpa.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody User user) {
        try {
            service.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving user: " + e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> userDetail(@PathVariable int id) {
        Optional<User> user = service.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with ID: " + id);
        }
    }

    @GetMapping("/details")
    public ResponseEntity<List<User>> getAllUserDetails() {
        List<User> users = service.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        try {
            service.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting user: " + e.getMessage());
        }
    }
    @GetMapping("/domain")
    public ResponseEntity<?> getUserByDomain(@RequestBody Map<String, String> payload) {
        try {
            String domain = payload.get("domain");

            if (domain == null || domain.isEmpty()) {
                return ResponseEntity.badRequest().body("Domain must be provided.");
            }

            // Assuming `getUserByEmailDomain` returns a ResponseEntity or something compatible
            List<User> details = service.getUserByEmailDomain(domain);
            return ResponseEntity.ok(details);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error retrieving user by domain: " + e.getMessage());
        }
    }
    @PostMapping("/tasks")
    public ResponseEntity<?> userWithTask(@RequestBody User user) {
        try {
            // Ensure tasks are properly associated
            if (user.getTask() != null) {
                for (Tasks task : user.getTask()) {
                    task.setUser(user);
                }
            }

            // Save the user and tasks
            service.addUserWithTask(user);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("User and tasks saved successfully. ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving user and tasks: " + e.getMessage());
        }
    }

}
