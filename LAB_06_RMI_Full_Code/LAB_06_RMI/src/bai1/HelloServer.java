package bai1;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class HelloServer extends UnicastRemoteObject implements HelloService {

    private static final long serialVersionUID = 1L;

	protected HelloServer() throws RemoteException {
        super();
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("HelloService", new HelloServer());
            System.out.println("HelloServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
