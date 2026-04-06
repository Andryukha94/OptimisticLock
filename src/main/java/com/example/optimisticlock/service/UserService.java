package com.example.optimisticlock.service;

import com.example.optimisticlock.dto.UserDTO;
import com.example.optimisticlock.dto.UserPatchDTO;
import com.example.optimisticlock.entity.User;
import com.example.optimisticlock.exception.UserNotFoundException;
import com.example.optimisticlock.mapper.UserMapper;
import com.example.optimisticlock.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(readOnly = true)
    public User findEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());
    }

    @Transactional (readOnly = true)
    public List<UserDTO> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @Transactional (readOnly = true)
    public UserDTO getOne(Long id) {
        return userMapper.toDto(findEntityById(id));
    }

    @Transactional
    public UserDTO create(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = findEntityById(id);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        userMapper.updateFromDto(user, userDTO);
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDTO updatePartial(Long id, UserPatchDTO userPatchDTO) {
        User user = findEntityById(id);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        userMapper.updatePartial(user, userPatchDTO);
        return userMapper.toDto(user);
    }

    @Transactional
    public void delete (Long id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }
}
