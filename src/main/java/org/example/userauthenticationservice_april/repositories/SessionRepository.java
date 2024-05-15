package org.example.userauthenticationservice_april.repositories;

import org.example.userauthenticationservice_april.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session,Long> {
}
