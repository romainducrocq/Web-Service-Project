package rentalclient;


import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import employeedb.Employee;
import shared.IEmployee;
import shared.IEmployeeDB;
import shared.IVehicle;
import shared.IVehicleParkRentalManagement;

import java.net.MalformedURLException;

public class RentalClient {
	
	public static void main(String[] args) {
		try {
			IVehicleParkRentalManagement rentalMgt = (IVehicleParkRentalManagement) Naming.lookup("//localhost:1099/rentalmanagement");
			Collection<IVehicle> listVehicle = rentalMgt.getAvailableVehiclesForRental();
			
			displayListVehicles(listVehicle);
			
			IEmployeeDB employeeDB = (IEmployeeDB) Naming.lookup("//localhost:2000/employeeDB");
						
			System.out.println("Dans RENTAL CLIENT *******************************************");
			
			System.out.println("Is Natacha an employee ? "+employeeDB.isEmployee("Natacha"));
			System.out.println("Is Olie an employee ? "+employeeDB.isEmployee("Olie"));
			
			IEmployee nat = employeeDB.getEmployee("Natacha");
			IEmployee romain = employeeDB.getEmployee("Romain");
			IEmployee alex = employeeDB.getEmployee("Alexandre");
			
			
			
			rentalMgt.rentVehicle(nat, 0);
			
			
			rentalMgt.rentVehicle(romain, 0);
			
			listVehicle = rentalMgt.getAvailableVehiclesForRental();
			
			displayListVehicles(listVehicle);
			
			rentalMgt.returnCar(0, 12, "My dog has dirty the car... sorry... :(");

			listVehicle = rentalMgt.getAvailableVehiclesForRental();
			displayListVehicles(listVehicle);

			rentalMgt.returnCar(0, 16, "It seems that a dog has dirtied the car in the previous rental... :(");

			listVehicle = rentalMgt.getAvailableVehiclesForRental();
			displayListVehicles(listVehicle);

		}
		catch (MalformedURLException me) {
			me.printStackTrace();
		}
		catch (RemoteException re) {
			System.out.println("Server-cient error : "+re.getMessage());
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void displayListVehicles(Collection<IVehicle> listVehicle) throws RemoteException {
		Iterator<IVehicle> it = listVehicle.iterator();
		System.out.println("**********************    AVAILABLE CARS    **********************");
		while (it.hasNext()) {
			IVehicle v = it.next();
			System.out.println(v.getDisplayString());
		}
	}
}