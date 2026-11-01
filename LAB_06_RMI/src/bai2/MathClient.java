package bai2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class MathClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            MathService service = (MathService) registry.lookup("MathService");

            System.out.print("Nhập số nguyên a: ");
            int a = sc.nextInt();
            System.out.print("Nhập số nguyên b: ");
            int b = sc.nextInt();

            int result = service.add(a, b);
            System.out.println("Tổng " + a + " + " + b + " = " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
