package com.project.ticketadministrations.DAO;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.project.ticketadministrations.DTO.User;
import com.project.ticketadministrations.Repository.UserRepository;

@Repository
public class UserDao {
    @Autowired
    private UserRepository repository;

    public User saveUser(User user) {
        return repository.save(user);
    }

    public User updateUser(User user) {
        return repository.save(user);
    }

    public List<User> getallUser() {
        return repository.findAll();
    }

    public Optional<User> findById(int id) {
        return repository.findById(id);
    }

    public Optional<User> verifyEmailPassword(String email, String password) {
        return repository.verifyByEmailandPassword(email, password);
    }

    public Optional<User> verifyPhonePassword(long phone, String password, String type) {
        return repository.verfyByPhoneandPassword(phone, password, type);
    }

}
