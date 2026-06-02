package com.duan;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DuAnJavaApp {
    private static final List<Product> products = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in, "UTF-8");

    public static void main(String[] args) {
        System.out.println("=== Ứng dụng quản lý sản phẩm Java ===");
        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    addProduct();
                    break;
                case "2":
                    listProducts();
                    break;
                case "3":
                    searchProducts();
                    break;
                case "4":
                    removeProduct();
                    break;
                case "5":
                    running = false;
                    System.out.println("Kết thúc chương trình. Cảm ơn!");
                    break;
                default:
                    System.out.println("Vui lòng chọn số từ 1 đến 5.");
                    break;
            }
        }
    }

    private static void showMenu() {
        System.out.println();
        System.out.println("1. Thêm sản phẩm");
        System.out.println("2. Hiển thị danh sách sản phẩm");
        System.out.println("3. Tìm kiếm theo tên");
        System.out.println("4. Xóa sản phẩm theo mã");
        System.out.println("5. Thoát");
        System.out.print("Chọn chức năng: ");
    }

    private static void addProduct() {
        System.out.print("Nhập mã sản phẩm: ");
        String id = scanner.nextLine().trim();
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine().trim();
        System.out.print("Nhập giá sản phẩm: ");
        String priceText = scanner.nextLine().trim();
        double price;
        try {
            price = Double.parseDouble(priceText);
        } catch (NumberFormatException e) {
            System.out.println("Giá không hợp lệ. Vui lòng nhập số.");
            return;
        }
        products.add(new Product(id, name, price));
        System.out.println("Đã thêm sản phẩm: " + name);
    }

    private static void listProducts() {
        System.out.println("=== Danh sách sản phẩm ===");
        if (products.isEmpty()) {
            System.out.println("Chưa có sản phẩm nào.");
            return;
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private static void searchProducts() {
        System.out.print("Nhập tên cần tìm: ");
        String keyword = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase(Locale.ROOT).contains(keyword)) {
                results.add(product);
            }
        }
        if (results.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm phù hợp.");
            return;
        }
        System.out.println("Kết quả tìm kiếm:");
        results.forEach(System.out::println);
    }

    private static void removeProduct() {
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String id = scanner.nextLine().trim();
        boolean removed = products.removeIf(product -> product.getId().equalsIgnoreCase(id));
        if (removed) {
            System.out.println("Đã xóa sản phẩm mã " + id);
        } else {
            System.out.println("Không tìm thấy sản phẩm với mã đó.");
        }
    }

    private static class Product {
        private final String id;
        private final String name;
        private final double price;

        public Product(String id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s - %.2f VND", id, name, price);
        }
    }
}
