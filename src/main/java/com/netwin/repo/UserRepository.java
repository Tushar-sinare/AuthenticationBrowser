package com.netwin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.netwin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}