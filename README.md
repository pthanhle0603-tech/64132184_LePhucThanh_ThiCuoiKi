# dự án java

## Giới thiệu

Dự án Java này gồm hai phần:

- một ứng dụng console đơn giản để quản lý sản phẩm
- một ứng dụng web Java đơn giản với Spring Boot và giao diện HTML/CSS/JavaScript

## Chức năng web

1. Xem danh sách sản phẩm
2. Thêm sản phẩm mới
3. Xóa sản phẩm

## Chạy ứng dụng web

Yêu cầu:
- Java 17+ 
- Maven

Từ thư mục `dự án java`:

```powershell
mvn clean package
mvn spring-boot:run
```

Sau đó mở trình duyệt:

```text
http://localhost:8080
```

## Chạy ứng dụng console

Đây vẫn là ứng dụng console mẫu nếu bạn muốn chạy:

```powershell
javac src\main\java\com\duan\DuAnJavaApp.java
java -cp src\main\java com.duan.DuAnJavaApp
```

## Cấu trúc thư mục

- `pom.xml` - cấu hình Maven cho ứng dụng web
- `src/main/java/com/duan/javaweb` - mã nguồn Spring Boot cho web app
- `src/main/resources/static` - HTML/CSS/JS giao diện người dùng
- `src/main/resources/application.properties` - cấu hình ứng dụng
- `src/main/java/com/duan/DuAnJavaApp.java` - ứng dụng console cũ
- `bao-cao.md` - báo cáo mô tả dự án
- `demo/demo-video.mp4` - file video demo nếu có

## Video demo

Hiện tại đây là placeholder. Nếu bạn có file video quay demo, đặt vào:
- `demo/demo-video.mp4`

## GitHub

Repo đã được commit cục bộ và push đến GitHub.
