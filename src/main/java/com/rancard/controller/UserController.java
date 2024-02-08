package com.rancard.controller;

import com.rancard.model.User;
import com.rancard.model.dto.user.CreateUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

interface UserController {

    @PostMapping
    ResponseEntity<User> create(@RequestBody CreateUserDto createUserDto);

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable Long id);

}
