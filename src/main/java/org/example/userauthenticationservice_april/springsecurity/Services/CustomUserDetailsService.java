package org.example.userauthenticationservice_april.springsecurity.Services;

import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.repositories.UserRepository;
import org.example.userauthenticationservice_april.springsecurity.Models.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);

        if(optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("NOT FOUND");
        }

        return new CustomUserDetails(optionalUser.get());

    }
}
