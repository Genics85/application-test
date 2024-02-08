package com.rancard.service;

import com.rancard.model.User;
import com.rancard.model.dto.user.CreateUserDto;
import jakarta.validation.Valid;

public interface UserService {

    User getById(Long id);

    User create( CreateUserDto createUserDto);



}
