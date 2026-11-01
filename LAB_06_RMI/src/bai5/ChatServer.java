package bai5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ChatServer {
    public static void main(String[] args) {
        try {
            ChatService service = new ChatServiceImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", service);
            System.out.println("ChatServer đang chạy trên cổng 1099. Đợi tin nhắn từ Client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
