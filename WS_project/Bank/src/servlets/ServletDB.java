package servlets;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shared.IBankDB;

/**
 * Servlet implementation class ServletDB
 */
public class ServletDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	IBankDB bankDB;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDB() {
    	try {
    		bankDB = (IBankDB) Naming.lookup("//localhost:2001/BankDB");
    	} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> l = bankDB.getAllBank();
		request.setAttribute("db", l);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/db.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
