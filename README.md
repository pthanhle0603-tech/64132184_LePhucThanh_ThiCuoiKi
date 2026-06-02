# dự án java

## Giới thiệu

Dự án Java này là một ứng dụng console quản lý sản phẩm đơn giản. Mục tiêu là minh họa cấu trúc dự án Java, các chức năng cơ bản và cách chạy chương trình.

## Chức năng

1. Thêm sản phẩm mới
2. Hiển thị danh sách sản phẩm
3. Tìm kiếm sản phẩm theo tên
4. Xóa sản phẩm theo mã
5. Thoát chương trình

## Cài đặt và chạy

Yêu cầu:
- Java 8 hoặc mới hơn

Biên dịch:
```powershell
cd "dự án java"
javac src\main\java\com\duan\DuAnJavaApp.java
```

Chạy ứng dụng:
```powershell
java -cp src\main\java com.duan.DuAnJavaApp
```

Hoặc biên dịch vào thư mục output:
```powershell
javac -d out src\main\java\com\duan\DuAnJavaApp.java
java -cp out com.duan.DuAnJavaApp
```

## Cấu trúc thư mục

- `src/main/java/com/duan/DuAnJavaApp.java` - mã nguồn chính
- `README.md` - mô tả dự án và cách chạy
- `bao-cao.md` - báo cáo dự án
- `demo/demo-video.mp4` - file video demo nếu có

## Video demo

Hiện tại đây là placeholder. Nếu bạn có file video quay demo, đặt vào:
- `demo/demo-video.mp4`

## GitHub

Repo đã được commit cục bộ. Để push lên GitHub, bạn có thể tạo repo trên GitHub rồi chạy:
```powershell
git remote add origin <repository-url>
git push -u origin master
```
