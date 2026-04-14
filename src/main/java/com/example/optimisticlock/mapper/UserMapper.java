package com.example.optimisticlock.mapper;

import com.example.optimisticlock.dto.UserDTO;
import com.example.optimisticlock.dto.UserPatchDTO;
import com.example.optimisticlock.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDTO toDto(User user) {
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getPhone(),
                user.getEmail(),
                user.getAge()
        );
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setPhone(dto.getPhone());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return user;
    }

    public void updateFromDto(User user, UserDTO userDto) {
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setAge(userDto.getAge());
    }

    public void updatePartial(User user, UserPatchDTO userDto) {
        if (userDto.getFirstName() != null) {
            user.setFirstName(userDto.getFirstName());
        }
        if (userDto.getLastName() != null) {
            user.setLastName(userDto.getLastName());
        }
        if (userDto.getPhone() != null) {
            user.setPhone(userDto.getPhone());
        }
        if (userDto.getEmail() != null) {
            user.setEmail(userDto.getEmail());
        }
        if (userDto.getAge() != null) {
            user.setAge(userDto.getAge());
        }
    }

}
