package com.xindus.wishlist.Services;

import com.xindus.wishlist.Models.User;
import com.xindus.wishlist.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public String createUser(User user) {
        userRepository.save(user);
        return "User added successfully";
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String updateUser(User updatedUser) {
        Long id = updatedUser.getUserId();
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            user.setUserName(updatedUser.getUserName());
            user.setUserPassword(updatedUser.getUserPassword());
            userRepository.save(user);
            return "User with id : "+ id  + "  updated";
        }
        else{
            return "User not found for id: " + id;
        }
    }

    public String deleteUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null){
            userRepository.delete(user);
            return "User with id : " + id + " has been removed from Database";
        }
        else {
            return "User not found for id : " + id;
        }
    }
}