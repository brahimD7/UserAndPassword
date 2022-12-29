package com.example.intractivtest.repository;

import com.example.intractivtest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByName(String name);

    Optional<User> findUserByNameAndPassword(String name, String password);
}
