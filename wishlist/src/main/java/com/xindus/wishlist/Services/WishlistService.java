package com.xindus.wishlist.Services;

import com.xindus.wishlist.Models.Product;
import com.xindus.wishlist.Models.User;
import com.xindus.wishlist.Models.Wishlist;
import com.xindus.wishlist.Repositories.ProductRepository;
import com.xindus.wishlist.Repositories.UserRepository;
import com.xindus.wishlist.Repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WishlistService {

    @Autowired
    WishlistRepository wishlistRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public String createWishlist(String userName, String userPassword, Wishlist wishlistItem) {
        User user = userRepository.findByUserName(userName);

        if (user == null || !user.getUserPassword().equals(userPassword)) {
            return "Invalid username or password";
        }

        if (user.getWishlist() != null) {
            return "User already has a wishlist";
        }

        wishlistItem.setUser(user);
        wishlistRepository.save(wishlistItem);

        return "Wishlist created successfully";
    }

    public Object getUserWishlist(String userName, String userPassword) {
        User user = userRepository.findByUserName(userName);

        if (user == null || !user.getUserPassword().equals(userPassword)) {
            return "User does not have a wishlist. Create a wishlist first.";
        }

        return user.getWishlist() + " " + Wishlist.getProductList();

    }

    public String addItemToWishlist(String userName, String userPassword, Long productId) {
        User user = userRepository.findByUserName(userName);

        if (user == null || !user.getUserPassword().equals(userPassword)) {
            return "Invalid username or password";
        }

        Wishlist wishlistItem = user.getWishlist();
        if (wishlistItem == null) {
            return "User does not have a wishlist. Create a wishlist first.";
        }

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            wishlistItem.add(optionalProduct.get());
            wishlistRepository.save(wishlistItem);
            return "Product added to the wishlist";
        } else {
            return "Product not found";
        }
    }

    public String deleteItemById(String userName, String userPassword, Long productId) {
        User user = userRepository.findByUserName(userName);

        if (user == null || !user.getUserPassword().equals(userPassword)) {
            return "Invalid username or password";
        }

        Wishlist wishlistItem = user.getWishlist();
        if (wishlistItem == null) {
            return "User does not have a wishlist";
        }

        Product product = productRepository.findById(productId).orElse(null);
        if(product != null) {
            if (wishlistItem.removeProduct(product)) {
                wishlistRepository.save(wishlistItem);
                return "Product removed from the wishlist";
            } else {
                return "Product not found in the wishlist";
            }
        }
        else {
            return "Product doesn't exist";
        }
    }
}
