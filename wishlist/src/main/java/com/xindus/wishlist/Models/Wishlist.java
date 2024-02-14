package com.xindus.wishlist.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    private String wishlistName;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToMany
    @JoinColumn(name = "product_Id" , nullable = false)
    private static List<Product> productList = new ArrayList<>();

    public void add(Product product) {
        productList.add(product);
    }

    public static List<Product> getProductList() {
        return productList;
    }


    public boolean removeProduct(Product product) {
        productList.remove(product);
        return true;
    }
}
