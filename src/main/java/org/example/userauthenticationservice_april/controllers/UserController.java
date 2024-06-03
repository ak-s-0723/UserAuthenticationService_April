package org.example.userauthenticationservice_april.controllers;

import jakarta.ws.rs.Path;
import org.example.userauthenticationservice_april.Dtos.UserDto;
import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public UserDto getUserById(@PathVariable Long uid) {
        User user = userService.getUserById(uid);
        return getUserDto(user);
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
