package rentalserver;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import shared.IVehicle;

/**
 * This class is in charge of creating the connection with the MySQL vehicles database.
 * Is also in charge of communicating with this DB.
 * @author natacha
 *
 */
public class VehiclesDBManager {
	
	/**
	 * The connection to the employees database.
	 */
	Connection conn;

	
	/**
	 * The constructor initializes the connection to the vehicles database.
	 */
	public VehiclesDBManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/vehiclesDB", "root", "1Rootpwd!");
			
		
		} catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Loads the vehicles from the database and returns a HashMap indexed by the vehicle id and containing Vehicle objects.
	 * @return a HashMap indexed by the vehicle id and containing Vehicle objects.
	 * @throws RemoteException may occur if the vehicle can not be created.
	 */
	public HashMap<Integer, IVehicle> loadVehicles() throws RemoteException {
		HashMap<Integer, IVehicle> vehiclesDB = new HashMap<Integer, IVehicle>();
		
		String query = "select * from vehicle";
	    PreparedStatement st;
		try {
			st = conn.prepareStatement(query);
		
		    ResultSet rs = st.executeQuery();
		    while (rs.next()) {
		    	
		    	int id = rs.getInt("id");
		    	String make = rs.getString("make");
		    	String model = rs.getString("model");
		    	int year = rs.getInt("year");
		    	int seatingCapacity = rs.getInt("seating_capacity");
		    	String fuelType = rs.getString("fuel_type");		    	    
		    	String transmission = rs.getString("transmission");	
		    	float price = rs.getFloat("price_euros");
		    	
		    	IVehicle veh = new Vehicle(id, make, model, year, seatingCapacity, fuelType, transmission, price);
		    	
		    	vehiclesDB.put(Integer.valueOf(id), veh);
		    }
		    
		    return vehiclesDB;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

}

