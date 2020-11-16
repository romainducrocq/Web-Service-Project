package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rentalclient.RentalClient;
import shared.IVehicle;

@WebServlet("/shop")
public class ServletShoppingCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RentalClient parkMgt; 
	
    public ServletShoppingCart() {
        super();
        this.parkMgt = new RentalClient();
    }

    //https://tomcat.apache.org/tomcat-5.5-doc/servletapi/javax/servlet/http/HttpServletRequest.html
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;		
		dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(request, response);
		
	}		

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		
		List<String> vehicles = Arrays.asList(request.getParameter("vehicles").split(","));

		List<IVehicle> shoppingCart = new ArrayList<IVehicle>();
		for(int i = 0; i < vehicles.size(); i++) {
			shoppingCart.add(this.parkMgt.getParkMgt().getVehicleById(Integer.parseInt(vehicles.get(i))));	
		}
		
    	//TODO
    	//Get active currencies from bank ws 
		request.setAttribute("currencies", Arrays.asList(new String[]{"AED","AFN","ALL","AOA","ARS","AUD","AZN","BBD","BDT","BGN","BHD","BND","BOB","BRL","BSD","BWP","BYN","BZD","CAD","CDF","CHF","CLP","CNY","COP","CRC","CVE","CZK","DKK","DOP","DZD","EGP","ERN","ETB","EUR","FJD","GBP","GHS","GTQ","GYD","HKD","HNL","HRK","HTG","HUF","IDR","ILS","INR","IQD","ISK","JMD","JOD","JPY","KES","KHR","KMF","KRW","KWD","KZT","LBP","LKR","LYD","MAD","MDL","MKD","MMK","MOP","MRU","MUR","MXN","MYR","MZN","NAD","NGN","NIO","NOK","NPR","NZD","OMR","PAB","PEN","PGK","PHP","PKR","PLN","PYG","QAR","RON","RSD","RUB","SAR","SEK","SGD","SRD","THB","TMT","TND","TRY","TTD","TWD","TZS","UAH","UGX","USD","UYU","UZS","VND","XAF","XCD","XDR","XOF","XPF","YER","ZAR","ZMW"}));
		//
		request.setAttribute("selectedCurrency", "EUR");
		request.setAttribute("exchangeRate", (double)1);
		request.setAttribute("shoppingCart", shoppingCart);
		
		dispatcher = request.getRequestDispatcher("/WEB-INF/payment.jsp");
		dispatcher.forward(request, response);

	}
}