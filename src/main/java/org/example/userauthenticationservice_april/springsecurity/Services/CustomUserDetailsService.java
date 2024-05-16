package org.example.userauthenticationservice_april.springsecurity.Services;

import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.repositories.UserRepository;
import org.example.userauthenticationservice_april.springsecurity.Models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if(userOptional.isEmpty()) return null;

        //In this case if BCryptPasswordEncoder will be there, it will get considered, otherwise
        // we will not move from Login Page
        //return new CustomUserDetails(userOptional.get());

        //In this case, as it is string will be considered, whatever we provide on frontend
        return org.springframework.security.core.userdetails.User
                .withDefaultPasswordEncoder().username(userOptional.get().getEmail())
                .password(userOptional.get().getPassword())
                .build();
    }
}
