package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the methods to "ask" the employee database.
 * @author Natacha
 *
 */
public interface IEmployeeDB extends Remote {

	/**
	 * Checks if an employee with this identifier is part of the company.
	 * @param id the identifier to check
	 * @return true if there exists an employee with this identifier in the employee database.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	public boolean isEmployee(String id) throws RemoteException;
	
	
	/**
	 * Returns the IEmployee implementation object corresponding to this identifier.
	 * @param id the identifier of the employee looked for.
	 * @return an IEmployee implementation object with this identifier
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	public IEmployee getEmployee(String id) throws RemoteException;

	/**
	 * Checks if an employee with this identifier and a password is part of the company.
	 * @param id the identifier to check
	 * @param pwd the password to check
	 * @return true if there exists an employee with this identifier and this password in the employee database.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	boolean isEmployee(String userName, String pwd) throws RemoteException;
	
}