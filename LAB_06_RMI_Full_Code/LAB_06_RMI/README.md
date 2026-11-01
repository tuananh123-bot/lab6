# Thực Hành Lab 06 - Java RMI (Bài 1 - Bài 9)

## Cấu trúc thư mục
Dự án được cấu hình sẵn chuẩn Eclipse IDE và VS Code, chia thành 9 package tương ứng với 9 bài tập:
- `src/bai1`: Máy chủ RMI trả về chuỗi "Hello, World!" (`HelloService`, `HelloServer`, `HelloClient`)
- `src/bai2`: Tính tổng hai số nguyên (`MathService`, `MathServer`, `MathClient`)
- `src/bai3`: Kiểm tra số nguyên tố (`PrimeService`, `PrimeServer`, `PrimeClient`)
- `src/bai4`: Quản lý danh bạ với HashMap (`ContactService`, `ContactServer`, `ContactClient`)
- `src/bai5`: Chat 1-1 đơn giản với RMI (`ChatService`, `ChatServer`, `ChatClient`)
- `src/bai6`: Quản lý tài khoản ngân hàng (`BankService`, `BankServer`, `BankClient`)
- `src/bai7`: Tính diện tích hình học (`GeometryService`, `GeometryServer`, `GeometryClient`)
- `src/bai8`: Hệ thống đặt vé máy bay (`FlightService`, `FlightServer`, `FlightClient`)
- `src/bai9`: Hệ thống đấu giá trực tuyến thời gian thực (`AuctionService`, `AuctionClientCallback`, `AuctionServer`, `AuctionClient`)

## Hướng dẫn chạy
1. **Trên Eclipse IDE:** Chọn `File` -> `Import...` -> `General` -> `Existing Projects into Workspace` -> Chọn `Select archive file` (trỏ trực tiếp tới file ZIP) hoặc giải nén và chọn `Select root directory` -> `Finish`.
2. **Trên VS Code:** Giải nén file ZIP và chọn `File` -> `Open Folder...` mở thư mục `LAB_06_RMI`.
3. **Chạy chương trình:**
   - Ở mỗi bài (`bai1` -> `bai9`), chạy file `*Server.java` trước, sau đó chạy file `*Client.java`.
   - Lưu ý dừng (Terminate) tiến trình Server của bài cũ trên Console trước khi chạy Server của bài mới để tránh đụng cổng `1099`.
