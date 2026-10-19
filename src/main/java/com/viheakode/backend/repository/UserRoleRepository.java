package com.viheakode.backend.repository;

import com.viheakode.backend.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByUserIdAndRoleId(Long userId, Long roleId);
    UserRole findByUserIdAndRoleId(Long userId, Long roleId);
}
