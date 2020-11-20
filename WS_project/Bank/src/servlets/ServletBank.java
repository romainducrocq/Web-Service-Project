package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import shared.IBank;
import shared.IBankDB;
import com.currencysystem.webservices.currencyserver.*;

/**
 * Servlet implementation class ServletBank
 */
public class ServletBank extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	CurrencyServerSoap currencySystem;
	List<String> currencies;
    /**
     * @throws ServiceException 
     * @throws RemoteException 
     * @see HttpServlet#HttpServlet()
     */
    public ServletBank() throws ServiceException, RemoteException {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String message = "Bank message";
		request.setAttribute("variable", message);
		
		List<String> curr = currencies;
		request.setAttribute("currNames", curr);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/bank.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
