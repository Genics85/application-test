package com.rancard.service;

import com.rancard.model.User;
import com.rancard.model.dto.user.CreateUserDto;
import com.rancard.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).orElseThrow(()-> new ServiceException("User with id "+id+" not found"));
    }

    @Override
    public User create(CreateUserDto createUserDto) {

        User user = new User();
        user.setFirstName(createUserDto.getFirstName());
        user.setLastName(createUserDto.getLastName());
        user.setPhone(createUserDto.getPhone());

        userRepo.save(user);

        return user;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }
}
