package com.example.optimisticlock.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 50)
    private String lastName;

    @Size(max = 15)
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @Min(0)
    @Max(150)
    private Integer age;

}