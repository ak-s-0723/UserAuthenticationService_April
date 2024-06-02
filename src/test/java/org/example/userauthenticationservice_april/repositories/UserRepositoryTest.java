package org.example.userauthenticationservice_april.repositories;

import org.example.userauthenticationservice_april.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void addUsersToAwsDatabase() {
        User user = new User();
        user.setEmail("anurag");
        user.setPassword(bCryptPasswordEncoder.encode("anurag"));
        userRepository.save(user);
        User user2 = new User();
        user2.setEmail("palanivel");
        user2.setPassword(bCryptPasswordEncoder.encode("palanivel"));
        userRepository.save(user2);
    }

}