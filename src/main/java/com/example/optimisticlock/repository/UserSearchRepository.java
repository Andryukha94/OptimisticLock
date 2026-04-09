package com.example.optimisticlock.repository;

import com.example.optimisticlock.dto.UserSearchDTO;
import com.example.optimisticlock.entity.User;

import java.util.List;

public interface UserSearchRepository {
    List<User> search(UserSearchDTO userSearchDTO);
}