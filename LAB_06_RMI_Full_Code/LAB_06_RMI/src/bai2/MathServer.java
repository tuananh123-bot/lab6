package bai2;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MathServer extends UnicastRemoteObject implements MathService {

    protected MathServer() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("MathService", new MathServer());
            System.out.println("MathServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
