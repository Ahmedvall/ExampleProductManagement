package com.example.example_product_management.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Client extends Users {

    public Client(Long id, String username, String password, Set<Authority> authorities) {
        super(id, username, password, authorities);
    }
}
