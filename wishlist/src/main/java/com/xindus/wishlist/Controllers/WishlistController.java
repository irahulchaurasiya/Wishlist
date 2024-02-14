package com.xindus.wishlist.Controllers;

import com.xindus.wishlist.Models.Wishlist;
import com.xindus.wishlist.Services.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/create")
    public String createWishlist(@RequestParam String userName ,@RequestParam String userPassword,@RequestBody Wishlist wishlistItem){
        return wishlistService.createWishlist(userName,userPassword, wishlistItem);
    }

    @GetMapping
    public Object getUserWishlist(@RequestParam String userName ,@RequestParam String userPassword){
        return wishlistService.getUserWishlist(userName,userPassword);
    }

    @PostMapping
    public String addItemToWishlist(@RequestParam String userName ,@RequestParam String userPassword , @RequestParam Long productId){
        return wishlistService.addItemToWishlist(userName,userPassword,productId);
    }

    @DeleteMapping
    public String deleteItemById(@RequestParam String userName ,@RequestParam String userPassword , @RequestParam Long productId){
        return wishlistService.deleteItemById(userName,userPassword,productId);
    }

}
