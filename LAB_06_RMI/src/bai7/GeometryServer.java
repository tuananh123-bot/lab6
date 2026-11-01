package bai7;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class GeometryServer {
    public static void main(String[] args) {
        try {
            GeometryService service = new GeometryServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("GeometryService", service);
            System.out.println("GeometryServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
