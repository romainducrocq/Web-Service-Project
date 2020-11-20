package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import com.currencysystem.webservices.currencyserver.*;
import shared.IBankDB;

/**
 * Servlet implementation class Converter
 */
public class ServletConverter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	CurrencyServerSoap currencySystem;
	List<String> currencies;
    /**
     * @throws ServiceException 
     * @throws RemoteException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletConverter() throws ServiceException, RemoteException {
    	CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();
    }
    
    public double getExRate(String curr, double dble) throws ServiceException, RemoteException {
		CurrencyServerSoap currencySystem = new CurrencyServerLocator().getCurrencyServerSoap();	
		return (double)currencySystem.convert("", "EUR", curr, dble, false, "", CurncsrvReturnRate.curncsrvReturnRateNumber, "", "");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		IBankDB bankDB = null;
		try {
			bankDB = (IBankDB) Naming.lookup("//localhost:2001/BankDB");
		} catch (MalformedURLException | RemoteException | NotBoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String bankid = request.getParameter("bankid");
		
		/**
		 * If the bank is known.
		 */
		if (bankDB.isBank(bankid)) {
			int cb_num = Integer.parseInt(request.getParameter("cb_num"));
			String exp = request.getParameter("exp");
			int cvv = Integer.parseInt(request.getParameter("cvv"));
			String f_n = request.getParameter("f_n");
			String l_n = request.getParameter("l_n");
			double amountSelected = Double.parseDouble(request.getParameter("amount"));
			double exchangeRate = -1;
			
			/**
			 * Check if the bank details are correct.
			 */
			if (bankDB.getbank(bankid).checkBankDetails(cb_num, exp, cvv, l_n, f_n)) {
				try {
					/**
					 * Check if there is enough fund in bank.
					 */
					if (bankDB.getbank(bankid).checkEnoughBalance(amountSelected)) {
						exchangeRate = bankDB.getbank(bankid).doConvertion(amountSelected);
						bankDB.getbank(bankid).withdraw(exchangeRate);
						request.setAttribute("convertion", exchangeRate);
						request.setAttribute("currencyName", bankDB.getbank(bankid).getCurrency());
					} else {
						request.setAttribute("convertion", 0);
						request.setAttribute("currencyName", "No currency");
					}
				} catch (RemoteException | ServiceException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				request.setAttribute("convertion", exchangeRate);
				request.setAttribute("currencyName", "No currency");
			}
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/converter.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
