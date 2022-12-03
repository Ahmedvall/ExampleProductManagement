package com.example.example_product_management.dtos;

import com.example.example_product_management.entities.Authority;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Builder
@Data
public class UserDto {
    private String username;
    private Set<Authority> authorities;
}
