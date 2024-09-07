package com.project.ticketadministrations.Controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.ticketadministrations.DTO.ResponseStructure;
import com.project.ticketadministrations.DTO.Tickets;
import com.project.ticketadministrations.DTO.User;
import com.project.ticketadministrations.Service.UserService;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService ser;

    @PostMapping("/user")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User u) {
        System.out.println(u);
        return ser.save(u);
    }

    @GetMapping("/")
    public String getMethodName() {
        String age = "Jerold";
        return age;
    }

    // Get by email and password
    @GetMapping("/verifyEmail")
    public ResponseEntity<ResponseStructure<User>> getByEmailPassword(@RequestParam String email,
            @RequestParam String password) {
        return ser.verifyEmailPassword(email, password);
    }

    // Get by phone and password
    @GetMapping("/verifyPhone")
    public ResponseEntity<ResponseStructure<User>> getByPhonePassword(@RequestParam long phone,
            @RequestParam String password, @RequestParam String type) {
        return ser.verifyPhonePassword(phone, password, type);
    }

    // Get by Id
    @GetMapping("/user/{id}")
    public ResponseEntity<ResponseStructure<User>> getById(@PathVariable int id) {
        return ser.findById(id);
    }

    // Get all users
    @GetMapping("/user")
    public ResponseEntity<ResponseStructure<List<User>>> getallUser() {
        return ser.getAllUsers();
    }

}
