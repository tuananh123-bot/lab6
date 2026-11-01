package bai6;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class BankServiceImpl extends UnicastRemoteObject implements BankService {
    private double balance;

    public BankServiceImpl(double initialBalance) throws RemoteException {
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
}
