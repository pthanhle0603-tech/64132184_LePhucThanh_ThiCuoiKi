package com.duan.javaweb.service;

import com.duan.javaweb.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProductService {
    private final List<Product> products = new CopyOnWriteArrayList<>();

    public ProductService() {
        products.add(new Product("P001", "Bút bi", 12000));
        products.add(new Product("P002", "Tập vở", 18000));
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public void add(Product product) {
        products.add(product);
    }

    public boolean deleteById(String id) {
        return products.removeIf(product -> product.getId().equalsIgnoreCase(id));
    }
}
