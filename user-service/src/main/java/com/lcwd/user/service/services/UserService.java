package com.lcwd.user.service.services;

import com.lcwd.user.service.entities.User;

import java.util.List;

public interface UserService {
    //User operations

    //create method

    User saveUser(User user);

    //get all user
    List<User> getAllUser();

    //get single user from user id

    User getUser(String userId);

    //create methods for update and delete

    //To update the user
    User updateUser(String userId,User user);

    //To delete the user
    String deleteUserByUserId(String userId);


}
