package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import shared.IBank;

public interface IBank extends Remote {
	public String getId() throws RemoteException;
	public int getCardNum() throws RemoteException;
	public double getBalance() throws RemoteException;
	public String getCurrency() throws RemoteException;
	public String getLastName() throws RemoteException;
	public String getFirstName() throws RemoteException;
	public String getExpiration() throws RemoteException;
	public int getCvv() throws RemoteException;
	
	public void add_money(double amount) throws RemoteException;
	public void withdraw(double amount) throws RemoteException;
	
	public List<String> allCurrencies() throws RemoteException, ServiceException;
	public double doGenericConvertion1euro(String currency) throws RemoteException, ServiceException;
	public double doConvertion1euro() throws RemoteException, ServiceException;
	public double doConvertion(double amount) throws RemoteException, ServiceException;
	
	public boolean checkEnoughBalance(double price) throws RemoteException, ServiceException;
	public boolean checkBankDetails(int cb_num, String exp, int cvv, String l_n, String f_n) throws RemoteException;
	public boolean canWithdraw(int cb_num, String exp, int cvv, String l_n, String f_n, double amount) throws RemoteException, ServiceException;
}
