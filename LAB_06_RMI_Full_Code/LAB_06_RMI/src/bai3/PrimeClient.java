package bai3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class PrimeClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            PrimeService service = (PrimeService) registry.lookup("PrimeService");

            System.out.print("Nhập số nguyên cần kiểm tra: ");
            int n = sc.nextInt();

            if (service.isPrime(n)) {
                System.out.println(n + " là số nguyên tố.");
            } else {
                System.out.println(n + " không phải là số nguyên tố.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
