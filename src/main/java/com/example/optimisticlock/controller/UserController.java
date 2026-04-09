package com.example.optimisticlock.controller;

import com.example.optimisticlock.dto.UserDTO;
import com.example.optimisticlock.dto.UserPatchDTO;
import com.example.optimisticlock.dto.UserSearchDTO;
import com.example.optimisticlock.service.UserService;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getOne(@PathVariable Long id) {
        return userService.getOne(id);
    }

    @GetMapping("/search")
    public List<UserDTO> search(UserSearchDTO userSearch) {
        return userService.search(userSearch);
    }

    @PostMapping
    public UserDTO create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable Long id,
                          @RequestBody @Valid UserDTO userDTO) {
        return userService.update(id, userDTO);
    }

    @PatchMapping("/{id}")
    public UserDTO updatePatch(@PathVariable Long id,
                               @RequestBody @Valid UserPatchDTO userPatchDTO) {
        return userService.updatePartial(id, userPatchDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}