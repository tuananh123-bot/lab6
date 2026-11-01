package bai9;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AuctionServer {
    public static void main(String[] args) {
        try {
            AuctionService service = new AuctionServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionService", service);
            System.out.println("AuctionServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
