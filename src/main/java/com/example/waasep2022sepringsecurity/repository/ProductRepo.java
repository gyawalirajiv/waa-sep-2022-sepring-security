package com.example.waasep2022sepringsecurity.repository;

import com.example.waasep2022sepringsecurity.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends CrudRepository<Product, Integer> {
}
