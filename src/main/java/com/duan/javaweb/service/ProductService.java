package com.duan.javaweb.service;

import com.duan.javaweb.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class ProductService {
    private final List<Product> products = new CopyOnWriteArrayList<>();

    public ProductService() {
        // Thêm dữ liệu mẫu
        Product p1 = new Product("P001", "Bút bi xanh", 12000);
        p1.setCategory("Văn phòng");
        p1.setStock(100);
        p1.setDescription("Bút bi chất lượng cao");
        products.add(p1);
        
        Product p2 = new Product("P002", "Tập vở A4", 18000);
        p2.setCategory("Văn phòng");
        p2.setStock(50);
        p2.setDescription("Tập vở kiểu dài");
        products.add(p2);
    }

    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    public Optional<Product> findById(String id) {
        return products.stream()
                .filter(p -> p.getId().equalsIgnoreCase(id))
                .findFirst();
    }

    public void add(Product product) {
        if (!productExists(product.getId())) {
            products.add(product);
        }
    }

    public boolean update(Product updatedProduct) {
        Optional<Product> existing = findById(updatedProduct.getId());
        if (existing.isPresent()) {
            Product product = existing.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setCategory(updatedProduct.getCategory());
            product.setStock(updatedProduct.getStock());
            product.setDescription(updatedProduct.getDescription());
            product.setRating(updatedProduct.getRating());
            return true;
        }
        return false;
    }

    public boolean deleteById(String id) {
        return products.removeIf(product -> product.getId().equalsIgnoreCase(id));
    }

    public List<Product> search(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerKeyword)
                        || p.getDescription().toLowerCase().contains(lowerKeyword)
                        || p.getId().toLowerCase().contains(lowerKeyword))
                .toList();
    }

    public List<Product> findByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equalsIgnoreCase(category))
                .toList();
    }

    public boolean productExists(String id) {
        return findById(id).isPresent();
    }

    public long getTotal() {
        return products.size();
    }

    public double getAveragePrice() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0);
    }

    public List<String> getAllCategories() {
        return products.stream()
                .map(Product::getCategory)
                .distinct()
                .sorted()
                .toList();
    }
}
