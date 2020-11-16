package servlets;

	import java.io.IOException;

	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.annotation.WebServlet;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;

	import rentalclient.RentalClient;
	
@WebServlet("/index")
public class ServletWelcome extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private RentalClient parkMgt; 
	
    public ServletWelcome() {
    	super();
    	this.parkMgt = new RentalClient();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;		
		dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
		dispatcher.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatcher;
		request.setAttribute("parkMgt", this.parkMgt);
		dispatcher = request.getRequestDispatcher("/WEB-INF/shop.jsp");
		dispatcher.forward(request, response);
	}

}