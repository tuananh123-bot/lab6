package bai8;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class FlightClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FlightService service = (FlightService) registry.lookup("FlightService");

            while (true) {
                System.out.println("\n--- HỆ THỐNG ĐẶT VÉ MÁY BAY (VN123, VJ456, QH789) ---");
                System.out.println("1. Kiểm tra số ghế trống");
                System.out.println("2. Đặt vé");
                System.out.println("3. Hủy vé");
                System.out.println("0. Thoát");
                System.out.print("Chọn thao tác: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 0) break;

                System.out.print("Nhập mã chuyến bay: ");
                String flight = sc.nextLine();

                switch (choice) {
                    case 1:
                        int avail = service.availableSeats(flight);
                        if (avail >= 0) {
                            System.out.println("Chuyến bay " + flight + " còn " + avail + " ghế trống.");
                        } else {
                            System.out.println("Không tìm thấy chuyến bay.");
                        }
                        break;
                    case 2:
                        System.out.print("Nhập số ghế muốn đặt: ");
                        int bookSeats = Integer.parseInt(sc.nextLine());
                        if (service.bookTicket(flight, bookSeats)) {
                            System.out.println("Đặt vé thành công!");
                        } else {
                            System.out.println("Đặt vé thất bại (Không đủ ghế hoặc sai mã chuyến bay).");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập số ghế muốn hủy: ");
                        int cancelSeats = Integer.parseInt(sc.nextLine());
                        if (service.cancelBooking(flight, cancelSeats)) {
                            System.out.println("Hủy vé thành công!");
                        } else {
                            System.out.println("Hủy vé thất bại.");
                        }
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
