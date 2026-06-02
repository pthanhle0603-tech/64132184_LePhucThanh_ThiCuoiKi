package com.duan.javaweb.controller;

import com.duan.javaweb.model.Product;
import com.duan.javaweb.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.add(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        boolean removed = productService.deleteById(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
