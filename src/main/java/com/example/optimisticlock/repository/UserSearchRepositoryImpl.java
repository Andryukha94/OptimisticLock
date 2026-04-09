package com.example.optimisticlock.repository;

import com.example.optimisticlock.dto.UserSearchDTO;
import com.example.optimisticlock.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import jakarta.persistence.criteria.CriteriaQuery;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserSearchRepositoryImpl implements UserSearchRepository {
    private final EntityManager entityManager;

    public List<User> search(UserSearchDTO userSearch) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        cq.select(root);

        List<Predicate> predicates = new ArrayList<>();

        if (userSearch.getFirstName() != null && !userSearch.getFirstName().isEmpty()) {
            predicates.add(cb.like(root.get("firstName"), "%"+userSearch.getFirstName()+"%"));
        }
        if (userSearch.getLastName() != null && !userSearch.getLastName().isEmpty()) {
            predicates.add(cb.like(root.get("lastName"), "%"+userSearch.getLastName()+"%"));
        }
        if (userSearch.getPhone() != null && !userSearch.getPhone().isEmpty()) {
            predicates.add(cb.like(root.get("phone"), "%"+userSearch.getPhone()+"%"));
        }
        if (userSearch.getEmail() != null && !userSearch.getEmail().isEmpty()) {
            predicates.add(cb.like(root.get("email"), "%"+userSearch.getEmail()+"%"));
        }
        if (userSearch.getAge() != null && userSearch.getAge() > 0) {
            predicates.add(cb.equal(root.get("age"), userSearch.getAge()));
        }

        cq.where(predicates.toArray(new Predicate[0]));
        TypedQuery<User> query = entityManager.createQuery(cq);
        return query.getResultList();
    }
}