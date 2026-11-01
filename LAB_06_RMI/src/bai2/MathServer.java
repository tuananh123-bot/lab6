package bai2;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class MathServer {
    public static void main(String[] args) {
        try {
            MathService service = new MathServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MathService", service);
            System.out.println("MathServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
