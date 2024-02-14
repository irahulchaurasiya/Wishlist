package com.xindus.wishlist.Services;

import com.xindus.wishlist.Models.Product;
import com.xindus.wishlist.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    public String createUser(Product product) {
        productRepository.save(product);
        return "Product added Successfully";
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public String updateProduct(Product updatedProduct) {
        Long id = updatedProduct.getProductId();
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            product.setProductName(updatedProduct.getProductName());
            product.setProductPrice(updatedProduct.getProductPrice());
            productRepository.save(product);
            return "product with id : " + id + " updated successfully";
        }
        else{
            return "Product with id : " + id + " not exist";
        }
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String deleteProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if(product != null){
            productRepository.delete(product);
            return "product with id : " + id + " deleted successfully";
        }
        else{
            return "Product with id : " + id + " not exist";
        }
    }
}
