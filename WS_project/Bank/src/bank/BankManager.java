package bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import com.currencysystem.webservices.currencyserver.CurncsrvReturnRate;
import com.currencysystem.webservices.currencyserver.CurrencyServerLocator;


public class BankManager {
	
	/**
	 * 
	 * @return List off all currencies
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public String getAllCurrencies() throws RemoteException, ServiceException {
		return new CurrencyServerLocator().getCurrencyServerSoap().activeCurrencies("");
	}
	
	/**
	 * 
	 * @param currency
	 * @return exchange rate from currency to EUR
	 * @throws RemoteException
	 * @throws ServiceException
	 */
	public double getExchangeRate(String currency) throws RemoteException, ServiceException {
		return (double)(new CurrencyServerLocator().getCurrencyServerSoap().convert("", "EUR", currency, (double)1, false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", ""));
	}
	
	//see for mysql driver path over wsdl !
	//https://stackoverflow.com/questions/1585811/classnotfoundexception-com-mysql-jdbc-driver
	/**
	 * 
	 * @param cardNumber
	 * @param cvv
	 * @param expiracyDate
	 * @param name
	 * @param currency
	 * @param totalPrice
	 * @return true if payment info are correct and balance >= totalPrice, else return false
	 */
	public boolean isValidPayment(String cardNumber, String cvv, String expiracyDate, String name, String currency, double totalPrice) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankDB", "root", "1Rootpwd!");
			String query = "select balance " + 
					"from bank" + " " + 
					"where card_number='" + cardNumber + "' " +
					"and cvv='" + cvv + "' " +
					"and expiracy_date='" + expiracyDate + "' " +
					"and name='" + name + "' " +
					"and currency='" + currency + "';";
			
		    PreparedStatement st;
			try {
				st = conn.prepareStatement(query);
			
			    ResultSet rs = st.executeQuery();
			    while (rs.next()) {
			    	
			    	if(((double)rs.getFloat("balance")) >= totalPrice) {
				    	return true;
			    	}			    	
			    	return false;
			    }
			    
			    return false;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			return false;
		}
	}
}
