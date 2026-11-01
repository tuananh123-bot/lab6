package bai4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServer extends UnicastRemoteObject implements ContactService {
    private final Map<String, String> contacts = new HashMap<>();

    protected ContactServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void addContact(String name, String phone) throws RemoteException {
        contacts.put(name, phone);
        System.out.println("Đã lưu liên hệ: " + name + " - " + phone);
    }

    @Override
    public synchronized String findContact(String name) throws RemoteException {
        return contacts.get(name);
    }

    @Override
    public synchronized boolean deleteContact(String name) throws RemoteException {
        return contacts.remove(name) != null;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("ContactService", new ContactServer());
            System.out.println("ContactServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
