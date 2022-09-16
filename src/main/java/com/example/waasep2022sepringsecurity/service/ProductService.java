package com.example.waasep2022sepringsecurity.service;



import com.example.waasep2022sepringsecurity.entity.Product;

import java.util.List;

public interface ProductService {

    void save(Product p);

    void delete(int id);

    Product getById(int id);

    List<Product> getAll();
}
