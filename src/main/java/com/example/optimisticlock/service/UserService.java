package com.example.optimisticlock.service;

import com.example.optimisticlock.dto.UserDTO;
import com.example.optimisticlock.dto.UserPatchDTO;
import com.example.optimisticlock.dto.UserSearchDTO;
import com.example.optimisticlock.entity.User;
import com.example.optimisticlock.exception.UserNotFoundException;
import com.example.optimisticlock.mapper.UserMapper;
import com.example.optimisticlock.repository.UserRepository;
import com.example.optimisticlock.specifications.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@EnableRetry
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

    @Retryable(
            retryFor =  ObjectOptimisticLockingFailureException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )

    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User user = findEntityById(id);
        userMapper.updateFromDto(user, userDTO);
        userRepository.flush();
        return userMapper.toDto(user);
    }

    @Recover
    public UserDTO recover(ObjectOptimisticLockingFailureException exception) {
        throw new RuntimeException();
    }

    @Retryable(
            retryFor = ObjectOptimisticLockingFailureException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 2000)
    )

    @Transactional
    public UserDTO updatePartial(Long id, UserPatchDTO userPatchDTO) {
        User user = findEntityById(id);
        userMapper.updatePartial(user, userPatchDTO);
        userRepository.flush();
        return userMapper.toDto(user);
    }

    @Recover
    public UserDTO recover(ObjectOptimisticLockingFailureException ex, Long id, UserPatchDTO userPatchDTO) {
        throw new RuntimeException();
    }

    @Transactional
    public void delete (Long id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> search(UserSearchDTO filter, Pageable pageable) {
        return userRepository
                .findAll(UserSpecifications.byFilter(filter), pageable)
                .map(userMapper::toDto);
    }
}
