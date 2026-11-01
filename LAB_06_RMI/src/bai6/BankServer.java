package bai6;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BankServer {
    public static void main(String[] args) {
        try {
            BankService service = new BankServiceImpl(1000000.0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankService", service);
            System.out.println("BankServer đang chạy trên cổng 1099 (Số dư khởi tạo: 1,000,000)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
