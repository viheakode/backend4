package com.viheakode.backend.repository;

import com.viheakode.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    boolean existsByUsernameAndUserIdNot(String username, Long userId);
    boolean existsByEmailAndUserIdNot(String email, Long userId);

    Optional<User> findByUsername(String username);


}
