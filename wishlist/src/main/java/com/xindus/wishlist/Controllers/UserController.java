package com.xindus.wishlist.Controllers;

import com.xindus.wishlist.Models.User;
import com.xindus.wishlist.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public String createUser(@RequestBody User user){
        return userService.createUser(user);

    }

    @GetMapping("/getAll")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PutMapping("/update")
    public String updateUser(@RequestBody User updatedUser){
        return userService.updateUser(updatedUser);
    }

    @GetMapping("/id")
    public User getUserById(@RequestParam Long id){
        return userService.getUserById(id);
    }

    @DeleteMapping("/id")
    public String deleteUserById(@RequestParam Long id){
        return userService.deleteUserById(id);
    }

}
