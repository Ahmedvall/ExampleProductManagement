package com.example.example_product_management.mappers;

import com.example.example_product_management.dtos.UserDto;
import com.example.example_product_management.entities.Users;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public UserDto toDto(Users user) {
        return UserDto.builder()
                .username(user.getUsername())
                .authorities(user.getAuthorities())
                .build();
    }

    public List<UserDto> toDtos(List<Users> users ) {
        return users.stream().map(this::toDto).collect(Collectors.toList());
    }
}
