package bai8;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class FlightServer {
    public static void main(String[] args) {
        try {
            FlightService service = new FlightServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FlightService", service);
            System.out.println("FlightServer đang chạy trên cổng 1099 (Chuyến bay: VN123, VJ456, QH789)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
