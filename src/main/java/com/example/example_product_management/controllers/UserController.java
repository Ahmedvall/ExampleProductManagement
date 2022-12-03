package com.example.example_product_management.controllers;


import com.example.example_product_management.dtos.UserDto;
import com.example.example_product_management.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UsersService usersService;

    @GetMapping
    public List<UserDto> users() {
        return usersService.users();
    }

}
