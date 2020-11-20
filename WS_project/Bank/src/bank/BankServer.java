package bank;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * Start Bank server on port 2001
 */
public class BankServer {
	public static void main(String[] args) {
		try {
			
			BankDB bankDB = new BankDB();

			LocateRegistry.createRegistry(2001);
			Naming.rebind("//localhost:2001/BankDB", bankDB);
			
			System.out.println("Server launched and bank DB registered");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
