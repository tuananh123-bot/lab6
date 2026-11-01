package bai5;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatServer extends UnicastRemoteObject implements ChatService {
    private final Scanner sc = new Scanner(System.in);
    private String lastReply = "";

    protected ChatServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void sendMessage(String message) throws RemoteException {
        System.out.println("\n[Client]: " + message);
        System.out.print("[Server nhập phản hồi]: ");
        lastReply = sc.nextLine();
    }

    @Override
    public synchronized String receiveReply() throws RemoteException {
        return lastReply;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ChatService", new ChatServer());
            System.out.println("ChatServer đang chạy trên cổng 1099. Đợi tin nhắn từ Client...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
