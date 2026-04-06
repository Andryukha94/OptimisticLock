package com.example.optimisticlock.repository;

import com.example.optimisticlock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
