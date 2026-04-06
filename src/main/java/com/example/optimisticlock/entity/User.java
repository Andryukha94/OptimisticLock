package com.example.optimisticlock.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OptimisticLocking;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column (name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column (name = "phone", length = 35)
    private String phone;

    @Column (name = "email", length = 100)
    private String email;

    @Column (name = "age")
    private Integer age;

    @CreationTimestamp
    @Column (name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Version
    private Integer version;
}