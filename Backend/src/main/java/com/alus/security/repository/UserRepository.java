package com.alus.security.repository;

import com.alus.security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    boolean existsByUsername(String username);
    UserEntity findByUsername(String username);
}
