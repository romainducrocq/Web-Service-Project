package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public interface IBankDB extends Remote {
	public IBank getbank(String name) throws RemoteException;
	public void printBank() throws RemoteException;
	public ArrayList getAllBank() throws RemoteException;
	public boolean isBank(String id) throws RemoteException;
}
