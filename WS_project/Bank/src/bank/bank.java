package bank;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Arrays;
import java.util.List;

import javax.xml.rpc.ServiceException;

import shared.IBank;
import com.currencysystem.webservices.currencyserver.*;

/**
 * Creation of the bank object
 */
public class Bank extends UnicastRemoteObject implements IBank {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Id of the bank
	 */
	String id;
	
	/**
	 * Payment card number
	 */
	int card_number;
	
	/**
	 * CVV code number
	 */
	int cvv;
	
	/**
	 * Funds of the bank account
	 */
	double balance;
	
	/**
	 * Currency used by the bank
	 */
	String currency;
	
	/**
	 * Name of the owner of the bank account
	 */
	String last_name;
	
	/**
	 * First name of the owner of the bank account
	 */
	String first_name;
	
	/**
	 * Expiration date of the card number like MM/YY
	 */
	String expiration;
	
	
	public Bank() throws RemoteException {
		super();
	}

	/**
	 * Bank object initialization
	 */
	public Bank(String i, int card, String exp, int cvv, double balance, String c, String l_n, String f_n) throws RemoteException {
		this.id = i;
		this.card_number = card;
		this.expiration = exp;
		this.cvv = cvv;
		this.balance = balance;
		this.currency = c;
		this.last_name = l_n;
		this.first_name = f_n;
	}
	
	/**
	 * Getter of bank id
	 */
	public String getId() throws RemoteException {
		return this.id;
	}
	
	/**
	 * Getter of card number
	 */
	public int getCardNum() throws RemoteException {
		return this.card_number;
	}
	
	/**
	 * Getter of current balance
	 */
	public double getBalance() throws RemoteException {
		return this.balance;
	}
	
	/**
	 * Getter of the bank currency
	 */
	public String getCurrency() throws RemoteException {
		return this.currency;
	}
	
	/**
	 * Getter of owner last name
	 */
	public String getLastName() throws RemoteException {
		return this.last_name;
	}
	
	/**
	 * Getter of the owner first name
	 */
	public String getFirstName() throws RemoteException {
		return this.first_name;
	}
	
	/**
	 * Getter of the card expiration date
	 */
	public String getExpiration() throws RemoteException {
		return this.expiration;
	}
	
	/**
	 * Getter of the CVV number
	 */
	public int getCvv() throws RemoteException {
		return this.cvv;
	}
	
	/**
	 * Add money in the bank funds
	 */
	public void add_money(double amount) throws RemoteException {
		this.balance = this.balance + amount;
	}
	
	/**
	 * Withdraw money from the bank account
	 */
	public void withdraw(double amount) throws RemoteException {
		this.balance = this.balance - amount;
	}

	/**
	 * Get all currencies available
	 */
	public List<String> allCurrencies() throws RemoteException, ServiceException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		List<String> currencies = Arrays.asList(currencySystem.activeCurrencies("").split(";"));
			for (int i = 0; i < currencies.size(); i++) { 
				currencies.get(i);
			}
		return currencies;
	}
	
	/**
	 * Convertion of 1 EUR to a currency
	 * @param currency The selected currency
	 */
	public double doGenericConvertion1euro(String currency) throws RemoteException, ServiceException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		double exchangerate = (double)currencySystem.convert("", "EUR", currency, (double)1, 
				false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
		return exchangerate;
	}
	
	/**
	 * Convert 1 EUR to the bank currency
	 */
	public double doConvertion1euro() throws RemoteException, ServiceException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		double exchangerate = (double)currencySystem.convert("", "EUR", this.currency, (double)1, 
				false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
		return exchangerate;
	}
	
	/**
	 * Convert a selected amount in EUR to the bank currency
	 * @param amount The amount in EUR that will be converted
	 * @return exchangerate The converted amount
	 */
	public double doConvertion(double amount) throws RemoteException, ServiceException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
		double exchangerate = (double)currencySystem.convert("", "EUR", this.currency, amount, 
				false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
		return exchangerate;
	}	
	
	/**
	 * Verify if the bank has enough funds to withdraw the parameter amount of money
	 * @param price The amount, in EUR, that is asked to be withdraw
	 */
	public boolean checkEnoughBalance(double price) throws RemoteException, ServiceException {
		if (this.doConvertion(price) < this.balance) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verify if the bank details are rights
	 * @param cb_num, exp, cvv, l_n, f_n The card number, expiration date, cvv number, owner'last and first name
	 */
	public boolean checkBankDetails(int cb_num, String exp, int cvv, String l_n, String f_n) throws RemoteException {
		if (this.getExpiration().equals(exp) && this.card_number == cb_num && this.cvv == cvv
				&& this.last_name.equals(l_n) && this.first_name.equals(f_n)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Verify if the bank details are rights and if the amount selected can be withdraw
	 * @param cb_num, exp, cvv, l_n, f_n, amount:
	 * 		 The card number, expiration date, cvv number, owner'last and first name and amount in EUR
	 */
	public boolean canWithdraw(int cb_num, String exp, int cvv, String l_n, String f_n, double amount) throws RemoteException, ServiceException {
		if (this.getExpiration().equals(exp) && this.card_number == cb_num && this.cvv == cvv
				&& this.last_name.equals(l_n) && this.first_name.equals(f_n)) {
			if (this.doConvertion(amount) < this.balance) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}
		
}
