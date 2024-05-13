package org.example.userauthenticationservice_april.controllers;

import org.example.userauthenticationservice_april.Dtos.LoginRequestDto;
import org.example.userauthenticationservice_april.Dtos.LogoutRequestDto;
import org.example.userauthenticationservice_april.Dtos.SignupRequestDto;
import org.example.userauthenticationservice_april.Dtos.UserDto;
import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignupRequestDto signupRequestDto) {
        try {
            User user = authService.signUp(signupRequestDto.getEmail(), signupRequestDto.getPassword());
            return new ResponseEntity<>(getUserDto(user),HttpStatus.OK);
        }catch(Exception exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return null;
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        return null;
    }

    private UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setRoleSet(user.getRoleSet());
        return userDto;
    }
}
