package com.xindus.wishlist.Controllers;

import com.xindus.wishlist.Models.Product;
import com.xindus.wishlist.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create")
    public String createProduct(@RequestBody Product product){
        return productService.createUser(product);

    }

    @GetMapping("/getAll")
    public List<Product> getAllProduct(){
        return productService.getAllProduct();
    }

    @PutMapping("/update")
    public String updateProduct(@RequestBody Product updatedProduct){
        return productService.updateProduct(updatedProduct);
    }

    @GetMapping("/id")
    public Product getProductById(@RequestParam Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/id")
    public String deleteProductById(@RequestParam Long id){
        return productService.deleteProductById(id);
    }
}
