package com.works.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    private String title;
    private String detail;
    private Integer price;

    @ManyToMany
    List<Category> categories;

}
