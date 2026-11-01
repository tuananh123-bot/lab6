package bai5;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class ChatServiceImpl extends UnicastRemoteObject implements ChatService {
    private final Scanner sc = new Scanner(System.in);
    private String lastReply = "";

    public ChatServiceImpl() throws RemoteException {
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
}
