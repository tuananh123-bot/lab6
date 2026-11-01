package bai4;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class ContactServiceImpl extends UnicastRemoteObject implements ContactService {
    private final Map<String, String> contacts = new HashMap<>();

    public ContactServiceImpl() throws RemoteException {
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
}
