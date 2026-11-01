package bai6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class BankClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            BankService service = (BankService) registry.lookup("BankService");

            while (true) {
                System.out.println("\n--- NGÂN HÀNG RMI ---");
                System.out.println("1. Kiểm tra số dư");
                System.out.println("2. Gửi tiền");
                System.out.println("3. Rút tiền");
                System.out.println("0. Thoát");
                System.out.print("Chọn giao dịch: ");
                int choice = sc.nextInt();

                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        System.out.printf("Số dư hiện tại: %.2f\n", service.getBalance());
                        break;
                    case 2:
                        System.out.print("Nhập số tiền muốn gửi: ");
                        double dep = sc.nextDouble();
                        service.deposit(dep);
                        System.out.println("Đã gửi tiền thành công.");
                        break;
                    case 3:
                        System.out.print("Nhập số tiền muốn rút: ");
                        double wit = sc.nextDouble();
                        if (service.withdraw(wit)) {
                            System.out.println("Rút tiền thành công.");
                        } else {
                            System.out.println("Giao dịch thất bại (Số dư không đủ hoặc số tiền không hợp lệ).");
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
