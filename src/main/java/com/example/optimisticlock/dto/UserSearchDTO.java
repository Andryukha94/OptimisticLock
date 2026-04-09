package com.example.optimisticlock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSearchDTO {
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private Integer age;
}
