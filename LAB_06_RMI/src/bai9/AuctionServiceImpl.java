package bai9;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class AuctionServiceImpl extends UnicastRemoteObject implements AuctionService {

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
    private final List<ClientCallback> clients = new ArrayList<>();

    public AuctionServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized void registerClient(ClientCallback client) throws RemoteException {
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
}
