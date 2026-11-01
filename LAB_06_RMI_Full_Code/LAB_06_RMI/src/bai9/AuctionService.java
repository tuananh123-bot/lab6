package bai9;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface AuctionService extends Remote {
    void registerClient(AuctionClientCallback client) throws RemoteException;
    void addProduct(String productName, double startingPrice) throws RemoteException;
    List<String> getProducts() throws RemoteException;
    boolean placeBid(String productName, String bidderName, double bidAmount) throws RemoteException;
}
