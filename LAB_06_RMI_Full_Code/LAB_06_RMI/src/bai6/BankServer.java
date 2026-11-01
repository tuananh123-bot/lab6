package bai6;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BankServer extends UnicastRemoteObject implements BankService {
    private double balance;

    protected BankServer(double initialBalance) throws RemoteException {
        super();
        this.balance = initialBalance;
    }

    @Override
    public synchronized double getBalance() throws RemoteException {
        return balance;
    }

    @Override
    public synchronized void deposit(double amount) throws RemoteException {
        if (amount > 0) {
            balance += amount;
            System.out.println("Đã nạp: " + amount + " | Số dư mới: " + balance);
        }
    }

    @Override
    public synchronized boolean withdraw(double amount) throws RemoteException {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("Đã rút: " + amount + " | Số dư mới: " + balance);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BankService", new BankServer(1000000.0));
            System.out.println("BankServer đang chạy trên cổng 1099 (Số dư khởi tạo: 1,000,000)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
