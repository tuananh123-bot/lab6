package bai9;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuctionServer extends UnicastRemoteObject implements AuctionService {

    private static class Product {
        String name;
        double currentPrice;
        String highestBidder;

        Product(String name, double startingPrice) {
            this.name = name;
            this.currentPrice = startingPrice;
            this.highestBidder = "Chưa có";
        }
    }

    private final Map<String, Product> products = new LinkedHashMap<>();
    private final List<AuctionClientCallback> clients = new ArrayList<>();

    protected AuctionServer() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(AuctionClientCallback client) throws RemoteException {
        clients.add(client);
    }

    @Override
    public synchronized void addProduct(String productName, double startingPrice) throws RemoteException {
        products.put(productName.toLowerCase(), new Product(productName, startingPrice));
        System.out.println("Sản phẩm mới: " + productName + " - Giá khởi điểm: " + startingPrice);
    }

    @Override
    public synchronized List<String> getProducts() throws RemoteException {
        List<String> list = new ArrayList<>();
        for (Product p : products.values()) {
            list.add(String.format("Sản phẩm: %-15s | Giá hiện tại: %-10.2f | Người trả cao nhất: %s",
                    p.name, p.currentPrice, p.highestBidder));
        }
        return list;
    }

    @Override
    public synchronized boolean placeBid(String productName, String bidderName, double bidAmount) throws RemoteException {
        Product p = products.get(productName.toLowerCase());
        if (p != null && bidAmount > p.currentPrice) {
            p.currentPrice = bidAmount;
            p.highestBidder = bidderName;
            System.out.println("Cập nhật giá: " + p.name + " -> " + bidAmount + " bởi " + bidderName);
            notifyAllClients(p.name, bidderName, bidAmount);
            return true;
        }
        return false;
    }

    private void notifyAllClients(String productName, String bidderName, double newPrice) {
        clients.removeIf(client -> {
            try {
                client.notifyNewBid(productName, bidderName, newPrice);
                return false;
            } catch (RemoteException e) {
                return true;
            }
        });
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AuctionService", new AuctionServer());
            System.out.println("AuctionServer đang chạy trên cổng 1099...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
