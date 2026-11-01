package bai8;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FlightServer extends UnicastRemoteObject implements FlightService {
    private final Map<String, Integer> flights = new HashMap<>();

    protected FlightServer() throws RemoteException {
        super();
        flights.put("VN123", 50);
        flights.put("VJ456", 30);
        flights.put("QH789", 20);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        Integer available = flights.get(flightNumber.toUpperCase());
        if (available != null && seats > 0 && available >= seats) {
            flights.put(flightNumber.toUpperCase(), available - seats);
            return true;
        }
        return false;
    }

    @Override
    public synchronized int availableSeats(String flightNumber) throws RemoteException {
        return flights.getOrDefault(flightNumber.toUpperCase(), -1);
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        Integer available = flights.get(flightNumber.toUpperCase());
        if (available != null && seats > 0) {
            flights.put(flightNumber.toUpperCase(), available + seats);
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("FlightService", new FlightServer());
            System.out.println("FlightServer đang chạy trên cổng 1099 (Chuyến bay: VN123, VJ456, QH789)...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
