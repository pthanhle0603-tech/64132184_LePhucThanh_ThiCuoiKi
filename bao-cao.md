# Báo cáo dự án Java

## Mục tiêu

Xây dựng một dự án Java đơn giản có cả giao diện web và chức năng quản lý sản phẩm.

## Nội dung chính

- Ứng dụng web sử dụng Spring Boot để cung cấp API REST.
- Giao diện người dùng tĩnh được xây dựng bằng HTML, CSS và JavaScript.
- Dữ liệu sản phẩm được lưu tạm trong bộ nhớ (`ProductService`).

## Kiến trúc mã nguồn

- `pom.xml`: cấu hình Maven cho ứng dụng Spring Boot.
- `src/main/java/com/duan/javaweb/JavaWebDemoApplication.java`: lớp khởi động Spring Boot.
- `src/main/java/com/duan/javaweb/controller/ProductController.java`: API REST cho quản lý sản phẩm.
- `src/main/java/com/duan/javaweb/service/ProductService.java`: dịch vụ lưu trữ sản phẩm.
- `src/main/java/com/duan/javaweb/model/Product.java`: mô hình dữ liệu sản phẩm.
- `src/main/resources/static/index.html`: giao diện web chính.
- `src/main/resources/static/app.js`: JavaScript điều khiển tương tác.
- `src/main/resources/static/styles.css`: kiểu giao diện.

## Chức năng chính của web app

- Hiển thị danh sách sản phẩm.
- Thêm sản phẩm mới vào danh sách.
- Xóa sản phẩm theo mã.

## Cách chạy

1. Từ thư mục `dự án java`, chạy `mvn clean package`.
2. Chạy `mvn spring-boot:run`.
3. Mở `http://localhost:8080` trong trình duyệt.

## Ghi chú

- Đây là một ứng dụng web đơn giản phù hợp để học Java web cơ bản.
- Dữ liệu hiện chỉ lưu trong bộ nhớ, không dùng cơ sở dữ liệu.
- Ứng dụng console cũ vẫn còn trong repo nếu cần tham khảo.
