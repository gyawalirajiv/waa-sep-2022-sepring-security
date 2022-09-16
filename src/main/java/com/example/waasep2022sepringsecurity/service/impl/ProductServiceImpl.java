package com.example.waasep2022sepringsecurity.service.impl;

import com.example.waasep2022sepringsecurity.entity.Product;
import com.example.waasep2022sepringsecurity.entity.User;
import com.example.waasep2022sepringsecurity.repository.ProductRepo;
import com.example.waasep2022sepringsecurity.repository.UserRepo;
import com.example.waasep2022sepringsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public void save(Product p) {

        Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        p.setUser(userRepo.findByEmail(username));
        productRepo.save(p);
    }

    @Override
    public void delete(int id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product getById(int id) {
        return productRepo.findById(id).get();
    }

    @PreAuthorize("hasRole('ROLE_GOLD') or hasRole('ROLE_ADMIN')")
    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        productRepo.findAll().forEach(result::add);
        return result;
    }
}
