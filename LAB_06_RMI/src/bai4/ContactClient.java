package bai4;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ContactClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ContactService service = (ContactService) registry.lookup("ContactService");

            while (true) {
                System.out.println("\n--- QUẢN LÝ DANH BẠ ---");
                System.out.println("1. Thêm / Sửa liên hệ");
                System.out.println("2. Tìm kiếm liên hệ");
                System.out.println("3. Xóa liên hệ");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                int choice = Integer.parseInt(sc.nextLine());

                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        System.out.print("Nhập tên: ");
                        String name = sc.nextLine();
                        System.out.print("Nhập số điện thoại: ");
                        String phone = sc.nextLine();
                        service.addContact(name, phone);
                        System.out.println("Đã lưu thành công!");
                        break;
                    case 2:
                        System.out.print("Nhập tên cần tìm: ");
                        String searchName = sc.nextLine();
                        String foundPhone = service.findContact(searchName);
                        if (foundPhone != null) {
                            System.out.println("Số điện thoại của " + searchName + ": " + foundPhone);
                        } else {
                            System.out.println("Không tìm thấy liên hệ.");
                        }
                        break;
                    case 3:
                        System.out.print("Nhập tên cần xóa: ");
                        String delName = sc.nextLine();
                        if (service.deleteContact(delName)) {
                            System.out.println("Đã xóa liên hệ thành công.");
                        } else {
                            System.out.println("Không tồn tại liên hệ này.");
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
