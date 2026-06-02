package com.duan.javaweb.controller;

import com.duan.javaweb.model.Product;
import com.duan.javaweb.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllProducts() {
        Map<String, Object> response = new HashMap<>();
        response.put("products", productService.findAll());
        response.put("count", productService.findAll().size());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProductById(@PathVariable String id) {
        Optional<Product> product = productService.findById(id);
        Map<String, Object> response = new HashMap<>();
        if (product.isPresent()) {
            response.put("product", product.get());
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        response.put("message", "Không tìm thấy sản phẩm");
        response.put("status", "error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addProduct(@RequestBody Product product) {
        Map<String, Object> response = new HashMap<>();
        if (productService.productExists(product.getId())) {
            response.put("message", "Mã sản phẩm đã tồn tại");
            response.put("status", "error");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
        productService.add(product);
        response.put("message", "Thêm sản phẩm thành công");
        response.put("product", product);
        response.put("status", "success");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Map<String, Object> response = new HashMap<>();
        if (productService.update(product)) {
            response.put("message", "Cập nhật sản phẩm thành công");
            response.put("product", product);
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        response.put("message", "Không tìm thấy sản phẩm để cập nhật");
        response.put("status", "error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable String id) {
        Map<String, Object> response = new HashMap<>();
        if (productService.deleteById(id)) {
            response.put("message", "Xóa sản phẩm thành công");
            response.put("status", "success");
            return ResponseEntity.ok(response);
        }
        response.put("message", "Không tìm thấy sản phẩm để xóa");
        response.put("status", "error");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchProducts(@RequestParam String keyword) {
        Map<String, Object> response = new HashMap<>();
        var results = productService.search(keyword);
        response.put("results", results);
        response.put("count", results.size());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getProductsByCategory(@PathVariable String category) {
        Map<String, Object> response = new HashMap<>();
        var results = productService.findByCategory(category);
        response.put("results", results);
        response.put("count", results.size());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/categories")
    public ResponseEntity<Map<String, Object>> getCategories() {
        Map<String, Object> response = new HashMap<>();
        response.put("categories", productService.getAllCategories());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/list/stats")
    public ResponseEntity<Map<String, Object>> getStats() {
        Map<String, Object> response = new HashMap<>();
        response.put("total", productService.getTotal());
        response.put("averagePrice", productService.getAveragePrice());
        response.put("status", "success");
        return ResponseEntity.ok(response);
    }
}
