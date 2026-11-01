# Thực Hành Lab 06 - Java RMI (Cấu trúc chuẩn 4 File / Bài)

Mỗi bài tập từ Bài 1 đến Bài 9 được tách thành đúng **4 file** chuẩn mô hình RMI:
1. `*Service.java` (Remote Interface)
2. `*ServiceImpl.java` (Lớp triển khai kế thừa `UnicastRemoteObject`)
3. `*Server.java` (Khởi tạo `Registry` cổng 1099 và đăng ký dịch vụ)
4. `*Client.java` (Gọi phương thức từ xa)

## Danh sách các package trong `src/`
- `src/bai1`: `HelloService.java`, `HelloServiceImpl.java`, `HelloServer.java`, `HelloClient.java`
- `src/bai2`: `MathService.java`, `MathServiceImpl.java`, `MathServer.java`, `MathClient.java`
- `src/bai3`: `PrimeService.java`, `PrimeServiceImpl.java`, `PrimeServer.java`, `PrimeClient.java`
- `src/bai4`: `ContactService.java`, `ContactServiceImpl.java`, `ContactServer.java`, `ContactClient.java`
- `src/bai5`: `ChatService.java`, `ChatServiceImpl.java`, `ChatServer.java`, `ChatClient.java`
- `src/bai6`: `BankService.java`, `BankServiceImpl.java`, `BankServer.java`, `BankClient.java`
- `src/bai7`: `GeometryService.java`, `GeometryServiceImpl.java`, `GeometryServer.java`, `GeometryClient.java`
- `src/bai8`: `FlightService.java`, `FlightServiceImpl.java`, `FlightServer.java`, `FlightClient.java`
- `src/bai9`: `AuctionService.java`, `AuctionServiceImpl.java`, `AuctionServer.java`, `AuctionClient.java`
