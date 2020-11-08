package employeedb;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * The employee database server :
 * It creates and exports a Registry instance on the local host that accepts requests on the port 2000.
 * It creates a VehicleParkRentalManagement object and associate it with the name "employeeDB".
 * @author Natacha
 *
 */
public class EmployeeDBServer {

	public static void main(String[] args) {
		try {
			
			EmployeeDB employeeDB = new EmployeeDB();

			LocateRegistry.createRegistry(2000);
			Naming.rebind("//localhost:2000/employeeDB", employeeDB);
			
			System.out.println("Server launched and employee DB registered");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}