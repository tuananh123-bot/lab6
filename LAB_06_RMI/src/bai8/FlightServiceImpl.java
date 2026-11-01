package bai8;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    private final Map<String, Integer> flights = new HashMap<>();

    public FlightServiceImpl() throws RemoteException {
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
}
