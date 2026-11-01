package bai5;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatService service = (ChatService) registry.lookup("ChatService");

            System.out.println("Đã kết nối ChatServer (nhập 'exit' để thoát).");
            while (true) {
                System.out.print("[Client nhập tin nhắn]: ");
                String msg = sc.nextLine();
                if ("exit".equalsIgnoreCase(msg)) break;

                service.sendMessage(msg);
                String reply = service.receiveReply();
                System.out.println("[Server phản hồi]: " + reply);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
