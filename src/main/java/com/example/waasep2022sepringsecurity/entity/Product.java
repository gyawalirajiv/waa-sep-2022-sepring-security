package com.example.waasep2022sepringsecurity.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private float price;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @JsonManagedReference
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_user")
    private User user;

//    @JsonManagedReference
//    @ManyToMany(mappedBy = "products")
//    private List<Category> categories;

}
