package rentalclient;


import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import shared.IVehicleParkRentalManagement;

public class RentalClient {
	private IVehicleParkRentalManagement parkMgt = null;
	
	public RentalClient() {
		try {
			this.parkMgt = (IVehicleParkRentalManagement) Naming.lookup("//localhost:1099/rentalmanagement");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public IVehicleParkRentalManagement getParkMgt() {
		return this.parkMgt;
	}
}