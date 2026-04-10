package com.example.optimisticlock.dto;

import jakarta.validation.constraints.Min;
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

    @Min(1)
    private Integer age;
}
