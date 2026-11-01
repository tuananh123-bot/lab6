package bai9;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AuctionClientCallback extends Remote {
    void notifyNewBid(String productName, String bidderName, double newPrice) throws RemoteException;
}
