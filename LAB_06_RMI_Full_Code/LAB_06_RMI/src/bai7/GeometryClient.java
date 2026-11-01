package bai7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class GeometryClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            GeometryService service = (GeometryService) registry.lookup("GeometryService");

            while (true) {
                System.out.println("\n--- TÍNH DIỆN TÍCH HÌNH HỌC ---");
                System.out.println("1. Hình chữ nhật");
                System.out.println("2. Hình tròn");
                System.out.println("3. Hình tam giác");
                System.out.println("0. Thoát");
                System.out.print("Chọn hình: ");
                int choice = sc.nextInt();

                if (choice == 0) break;

                switch (choice) {
                    case 1:
                        System.out.print("Nhập chiều rộng và chiều cao: ");
                        double w = sc.nextDouble();
                        double h = sc.nextDouble();
                        System.out.printf("Diện tích HCN: %.2f\n", service.rectangleArea(w, h));
                        break;
                    case 2:
                        System.out.print("Nhập bán kính: ");
                        double r = sc.nextDouble();
                        System.out.printf("Diện tích Hình tròn: %.2f\n", service.circleArea(r));
                        break;
                    case 3:
                        System.out.print("Nhập cạnh đáy và chiều cao: ");
                        double b = sc.nextDouble();
                        double th = sc.nextDouble();
                        System.out.printf("Diện tích Hình tam giác: %.2f\n", service.triangleArea(b, th));
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
