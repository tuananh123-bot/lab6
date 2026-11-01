package bai3;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class PrimeServer {
    public static void main(String[] args) {
        try {
            PrimeService service = new PrimeServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("PrimeService", service);
            System.out.println("PrimeServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
