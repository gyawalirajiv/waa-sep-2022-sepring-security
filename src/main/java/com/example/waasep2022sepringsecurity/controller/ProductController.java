package com.example.waasep2022sepringsecurity.controller;


import com.example.waasep2022sepringsecurity.aspects.OffensiveFilter;
import com.example.waasep2022sepringsecurity.entity.Product;
import com.example.waasep2022sepringsecurity.entity.Review;
import com.example.waasep2022sepringsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @OffensiveFilter
    public void save(@RequestBody Product p) {
        productService.save(p);
    }

    @GetMapping
    public List<Product> getAll() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id) {
        Product product = productService.getById(id);
        return ResponseEntity.ok(product);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        productService.delete(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") int productId) {
        //call service
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Review> getReviewsByProductId(@PathVariable int id) {
        // for demo purposes, this request is not authorized.
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
    }

}
