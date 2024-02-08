package com.rancard.controller;

import com.rancard.model.User;
import com.rancard.model.dto.user.CreateUserDto;
import com.rancard.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserControllerImpl implements UserController {

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @Override
    public ResponseEntity<User> create(CreateUserDto createUserDto) {
        log.info("http request:create User");

        User user = userService.create(createUserDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @Override
    public ResponseEntity<User> getById(Long id) {
        log.info("http request:getById User");

        User user = userService.getById(id);

        return ResponseEntity.ok(user);

    }
}
