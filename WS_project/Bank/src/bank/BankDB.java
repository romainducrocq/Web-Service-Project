package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import shared.IBank;
import shared.IBankDB;

/**
 * Creation of the bank database
 */
public class BankDB extends UnicastRemoteObject implements IBankDB {
	private static final long serialVersionUID = 1L;
	
	HashMap<String, IBank> bankDB;
	
	/**
	 * Three banks are created with three different currencies: USD, PLN and EUR
	 */
	protected BankDB() throws RemoteException {
		this.bankDB = new HashMap<String, IBank>();
		
		Bank usd_bank = new Bank("usd_bank", 4020, "12/21", 100, 12000.0, "USD", "Davis", "Miles");
		Bank pln_bank = new Bank("pln_bank", 3020, "12/21", 789, 9000.0, "PLN", "Riedel", "Ryszard");
		Bank eu_bank = new Bank("eu_bank", 4310, "05/24", 444, 1200.0, "EUR", "Dupont", "Jean");
		
		bankDB.put(usd_bank.getId(), usd_bank);
		bankDB.put(pln_bank.getId(), pln_bank);
		bankDB.put(eu_bank.getId(), eu_bank);		
	}
	
	/**
	 * Access the bank by its id
	 * @param name The banks id 
	 */
	public IBank getbank(String name) throws RemoteException {
		return bankDB.get(name);
	}
	
	/**
	 * Maybe to remove
	 */
	public void printBank() throws RemoteException {
		for(Entry<String, IBank> entry : bankDB.entrySet()) {
		    String key = entry.getKey();
		    IBank value = entry.getValue();
		    System.out.println(key + " "+ value);
		}
	}
	
	/**
	 * Use in servletDB to print all banks informations
	 */
	public ArrayList getAllBank() throws RemoteException {
		ArrayList<String> l = new ArrayList<String>();
		for(Entry<String, IBank> entry : bankDB.entrySet()) {
		    String key = entry.getKey();
		    l.add(key);
		}
		
		return l;
	}
	
	/**
	 * Check if the parameter correspond to a bank id
	 * @param id The bank suspected id
	 */
	public boolean isBank(String id) throws RemoteException {
		return (bankDB.get(id) != null);
	}
	
}
