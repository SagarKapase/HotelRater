package com.lcwd.user.service.controller;

import com.lcwd.user.service.entities.User;
import com.lcwd.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-service")
public class UserController {

    @Autowired
    private UserService userService;

    //create user api
    @PostMapping("/addUser")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User user1=userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user1);
    }

    //get single user

    @GetMapping("/getUser/{userId}")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId){
        User user=userService.getUser(userId);
        return ResponseEntity.ok(user);
    }

    //get all users
    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUser=userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    //To update the user by user id
    @PutMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") String userId,@RequestBody User user){
        User updatedUser = userService.updateUser(userId, user);
        return ResponseEntity.ok(user);
    }

    //To delete user by userId
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") String userId){
        userService.deleteUserByUserId(userId);
        return ResponseEntity.ok("User has been deleted successfully...");
    }


}
