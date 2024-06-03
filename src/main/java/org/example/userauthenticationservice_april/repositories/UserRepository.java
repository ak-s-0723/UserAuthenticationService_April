package org.example.userauthenticationservice_april.repositories;

import org.example.userauthenticationservice_april.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByIdEquals(Long id);
}
