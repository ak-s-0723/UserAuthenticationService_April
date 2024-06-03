package org.example.userauthenticationservice_april.services;

import org.example.userauthenticationservice_april.models.User;
import org.example.userauthenticationservice_april.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findByIdEquals(id).get();
    }
}
