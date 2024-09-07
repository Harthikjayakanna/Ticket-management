package com.project.ticketadministrations.Service;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.ticketadministrations.DAO.UserDao;
import com.project.ticketadministrations.DTO.ResponseStructure;
import com.project.ticketadministrations.DTO.Tickets;
import com.project.ticketadministrations.DTO.User;
import com.project.ticketadministrations.Exception.IDnotFoundException;

@Service
public class UserService {
    @Autowired
    private UserDao dao;

    public ResponseEntity<ResponseStructure<User>> save(User u) {
        ResponseStructure<User> rUser = new ResponseStructure<>();
        System.out.println(u);
        rUser.setData(dao.saveUser(u));
        rUser.setMessage("User saved Successfully");
        rUser.setStatusCode(HttpStatus.CREATED.value());
        return new ResponseEntity<ResponseStructure<User>>(rUser, HttpStatus.CREATED);
    }

    public ResponseEntity<ResponseStructure<User>> update(User u) {
        ResponseStructure<User> rUser = new ResponseStructure<>();
        rUser.setData(dao.updateUser(u));
        rUser.setMessage("User saved Successfully");
        rUser.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<User>>(rUser, HttpStatus.OK);
    }

    // Get all Employee
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers() {
        ResponseStructure<List<User>> resu = new ResponseStructure<>();
        resu.setData(dao.getallUser());
        resu.setMessage("Got all Users");
        resu.setStatusCode(HttpStatus.OK.value());
        return new ResponseEntity<ResponseStructure<List<User>>>(resu,HttpStatus.OK);

    }

    // find by id
    public ResponseEntity<ResponseStructure<User>> findById(int id) {
        ResponseStructure<User> resU = new ResponseStructure<>();
        Optional<User> optU = dao.findById(id);
        if (optU.isPresent()) {
            resU.setData(optU.get());
            resU.setMessage("User Found");
            resU.setStatusCode(HttpStatus.OK.value());
            return new ResponseEntity<ResponseStructure<User>>(resU, HttpStatus.OK);
        }
        throw new IDnotFoundException();
    }

    // Find By Email and Password
    public ResponseEntity<ResponseStructure<User>> verifyEmailPassword(String email, String password) {
        ResponseStructure<User> resU = new ResponseStructure<>();
        Optional<User> optU = dao.verifyEmailPassword(email, password);
        if (optU.isPresent()) {
            resU.setData(optU.get());
            resU.setMessage("User verified");
            resU.setStatusCode(HttpStatus.FOUND.value());
            return new ResponseEntity<ResponseStructure<User>>(resU, HttpStatus.OK);
        }
        throw new IDnotFoundException();
    }

    // Find By Phone and Password
    public ResponseEntity<ResponseStructure<User>> verifyPhonePassword(long phone, String password, String type) {
        ResponseStructure<User> resU = new ResponseStructure<>();
        Optional<User> optU = dao.verifyPhonePassword(phone, password, type);
        if (optU.isPresent()) {
            resU.setData(optU.get());
            resU.setMessage("User verified");
            resU.setStatusCode(HttpStatus.FOUND.value());
            return new ResponseEntity<ResponseStructure<User>>(resU, HttpStatus.OK);
        }
        throw new IDnotFoundException();
    }

}
