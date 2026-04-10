package com.example.optimisticlock.specifications;

import com.example.optimisticlock.dto.UserSearchDTO;
import com.example.optimisticlock.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecifications {

    public static Specification<User> byFilter(UserSearchDTO filter) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (filter.getFirstName() != null && !filter.getFirstName().isEmpty()) {
                predicates.add(cb.like(root.get("firstName"), "%" + filter.getFirstName() + "%"));
            }
            if (filter.getLastName() != null && !filter.getLastName().isEmpty()) {
                predicates.add(cb.like(root.get("lastName"), "%" + filter.getLastName() + "%"));
            }
            if (filter.getPhone() != null && !filter.getPhone().isEmpty()) {
                predicates.add(cb.like(root.get("phone"), "%" + filter.getPhone() + "%"));
            }
            if (filter.getEmail() != null && !filter.getEmail().isEmpty()) {
                predicates.add(cb.like(root.get("email"), "%" + filter.getEmail() + "%"));
            }
            if (filter.getAge() != null) {
                predicates.add(cb.equal(root.get("age"), filter.getAge()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}