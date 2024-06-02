package org.example.userauthenticationservice_april.controllers;

import org.example.userauthenticationservice_april.Dtos.UserDto;
import org.example.userauthenticationservice_april.models.Role;
import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/{id}")
    public UserDto findUserById(@PathVariable Long id) {
        User user = userRepository.findByIdEquals(id).get();

        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
