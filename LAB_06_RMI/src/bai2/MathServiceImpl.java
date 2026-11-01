package bai2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MathServiceImpl extends UnicastRemoteObject implements MathService {

    public MathServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
