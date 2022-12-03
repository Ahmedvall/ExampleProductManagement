package com.example.example_product_management.configurations;

import com.example.example_product_management.entities.Admin;
import com.example.example_product_management.entities.Authority;
import com.example.example_product_management.entities.Client;
import com.example.example_product_management.entities.Product;
import com.example.example_product_management.repositories.AdminRepository;
import com.example.example_product_management.repositories.AuthorityRepository;
import com.example.example_product_management.repositories.ClientRepository;
import com.example.example_product_management.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Configuration
public class InitDataSet implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        List<Product> products = List.of(
                new Product(null, "HP", LocalDateTime.now(), LocalDateTime.now()),
                new Product(null, "DELL", LocalDateTime.now(), LocalDateTime.now()),
                new Product(null, "RAZER", LocalDateTime.now(), LocalDateTime.now())
        );
        productRepository.saveAll(products);


        Authority adminReadAuthority = authorityRepository.save(new Authority(null, "admin_read", null));
        Authority adminWriteAuthority = authorityRepository.save(new Authority(null, "admin_write", null));
        Authority clientReadAuthority = authorityRepository.save(new Authority(null, "client_read", null));
        Authority clientWriteAuthority = authorityRepository.save(new Authority(null, "client_write", null));


        Set<Authority> clientSet = Set.of(clientReadAuthority, clientWriteAuthority);
        Set<Authority> adminSet = Set.of(adminReadAuthority, adminWriteAuthority);
        List<Client> clients = clientRepository.saveAll(
                List.of(
                        new Client(null, "client1",  passwordEncoder.encode("client1"), clientSet),
                        new Client(null, "client2", passwordEncoder.encode("client1"), clientSet)
                )
        );

        List<Admin> admins = adminRepository.saveAll(
                List.of(
                        new Admin(null, "admin1", passwordEncoder.encode("admin1"), adminSet),
                        new Admin(null, "admin2", passwordEncoder.encode("admin2"), adminSet)
                )
        );


    }
}
