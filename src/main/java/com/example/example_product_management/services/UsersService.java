package com.example.example_product_management.services;

import com.example.example_product_management.dtos.UserDto;
import com.example.example_product_management.mappers.UserMapper;
import com.example.example_product_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserDto> users() {
        return userMapper.toDtos(userRepository.findAll());
    }
}
