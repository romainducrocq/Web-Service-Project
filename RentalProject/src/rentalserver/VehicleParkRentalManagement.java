package rentalserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import shared.IEmployee;
import shared.IVehicle;
import shared.IVehicleParkRentalManagement;

/**
 * Implementation of the vehicle park rental management module.
 * @author Natacha
 *
 */
public class VehicleParkRentalManagement extends UnicastRemoteObject implements IVehicleParkRentalManagement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String notificationSenderEmail = "ifscars2020@gmail.com";
	private String notificationSenderPwd = "ifscars?2";
	
	private Session mailSession;
	private String host = "smtp.gmail.com";
	
	/**
	 * Hash map indexed by the vehicle id and containing the IVehicle object this id as unique identifier.
	 */
	HashMap<Long,IVehicle> park;
	
	/**
	 * Counter to generate a unique vehicle identifier.
	 */
	long vehicleCounterForUniqueId;
		
	/**
	 * Hash map indexed by the vehicle id and containing for each vehicle, the list of employees renting
	 * or waiting for the rental.
	 * 
	 * If the array list is empty, then the car is not rented.
	 * The first element of the array list is the current renter.
	 * The next elements are the employees waiting for renting this car. 
	 */
	HashMap<Long, ArrayList<IEmployee>> rentalWaitingList;
	
	/**
	 * Initializes a new vehicle park rental module and load some vehicles.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	public VehicleParkRentalManagement() throws RemoteException {
		park = new HashMap<Long,IVehicle>();
		
		rentalWaitingList = new HashMap<Long, ArrayList<IEmployee>>();
		
		rentalWaitingList = new HashMap<Long, ArrayList<IEmployee>>();
		
		vehicleCounterForUniqueId = 0;
		
		loadVehicles();
		
		initializeEmailProtocol();

	}
	
	/**
	 * Loads a panel of vehicles.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	public void loadVehicles() throws RemoteException {		
		addVehicle("Renault", "Clio", 2020, 5, "Diesel", "Manual", 24000);
		addVehicle("Renault", "Clio", 2019, 5, "Gas", "Manual", 20000);
		addVehicle("Peugeot", "208", 2020, 5, "Gas", "Automatic", 23000);
		addVehicle("Peugeot", "308", 2019, 5, "Gas", "Manual", 21000);
		addVehicle("Renault", "Twingo", 2019, 4, "Gas", "Manual", 16000);
	}


	/**
	 * Returns the list of vehicles available for rental (all the cars in the park are "available" for rental.
	 * If a vehicle is already rent, the renter will be added to a waiting list for this car.
	 * @return the list of vehicles available for rental.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	@Override
	public List<IVehicle> getAvailableVehiclesForRental() throws RemoteException {
		// Can not return directly the values of the HashMap because it is not serializable... :(
		List<IVehicle> listVehiclesForRental = new ArrayList<IVehicle>();
		
		Collection<IVehicle> vehicles = park.values();
		Iterator<IVehicle> it = vehicles.iterator();
		while (it.hasNext()) {
			IVehicle v = it.next();
			listVehiclesForRental.add(v);   // all cars in the park are available ! Waiting list if already rented
		}
		return listVehiclesForRental;
	}
		

	/**
	 * Returns the list of vehicles available for sale.
	 * @return the list of vehicles available for sale.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	@Override
	public List<IVehicle> getAvailableVehiclesForSale() throws RemoteException {
		List<IVehicle> listVehiclesForSale = new ArrayList<IVehicle>();
		Collection<IVehicle> vehicles = park.values();
		Iterator<IVehicle> it = vehicles.iterator();
		while (it.hasNext()) {
			IVehicle v = it.next();
			if (v.isAvailableForSale()) {
				listVehiclesForSale.add(v);
			}
		}
		return listVehiclesForSale;
	}

	/**
	 * Tries to rent the vehicle for the specified employee. Notifies the employee to inform him/her of the rental result.
	 * Returns 	0 if the vehicle can not be rent (for example, it has been sold during the rental process),
	 * 			1 if the vehicle can be rent,
	 * 			2 if the vehicle is already rent and the employee will be put on a waiting list
	 * @param id the vehicle unique identifier
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	@Override
	public int rentVehicle(IEmployee employee, long vehicleId) throws RemoteException {
		IVehicle v = park.get(Long.valueOf(vehicleId));
		String msg = "";
		// If no vehicle found with this id, return 
		if (v == null) {
			try {
				msg = "Hi "+employee.getIdentifier()+"!\n\n We are SORRY but this vehicle can not be rented !";
				System.out.println(" DANS RENTCAR 1 : "+msg);
				notifyEmployee(employee, v, "IMPORTANT : Rental message", msg);
			} catch (MessagingException e) {
				System.out.println("ERROR IN EMPLOYEE NOTIFICATION ***");
				System.out.println("Unable to send an email with following message :");
				System.out.println(msg);
				
				e.printStackTrace();
			}
			return 0;
		}
		else {
			
			List<IEmployee> rentersList = rentalWaitingList.get(Long.valueOf(vehicleId));
			
			// Adds the employee to the rental list.
			rentersList.add(employee);
			
			// If there is only one employee in the rental list, then the car is rented by this employee.
			if (rentersList.size() == 1) {
				v.rentCar();
				try {
					msg = "Hi "+employee.getIdentifier()+"!\n\n You have rented this vehicle of id "+vehicleId+" ! Enjoy its use !!!";
					System.out.println(" DANS RENTCAR 2 : "+msg);
					notifyEmployee(employee, v, "IMPORTANT : Rental message", msg);
				} catch (MessagingException e) {
					System.out.println("ERROR IN EMPLOYEE NOTIFICATION ***");
					System.out.println("Unable to send an email with following message :");
					System.out.println(msg);
					
					e.printStackTrace();
				}
				return 1;
			}
			// Else the car is already rented, the employee has to wait
			else {
				try {
					msg = "Hi "+employee.getIdentifier()+"!\n\n Unfortunately, the car num "+vehicleId+" is already rented.\n You have been added to the waiting list and will be notified as soon as the vehicle is available.";
					System.out.println(" DANS RENTCAR 3 : "+msg);
					notifyEmployee(employee, v, "IMPORTANT : Rental message", msg);
				} catch (MessagingException e) {
					System.out.println("ERROR IN EMPLOYEE NOTIFICATION ***");
					System.out.println("Unable to send an email with following message :");
					System.out.println(msg);
					
					e.printStackTrace();
				}					
				return 2;
			}
		}
	}

	/**
	 * Initialize the email process.
	 */
	public void initializeEmailProtocol() {

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		boolean sessionDebug = true;


		Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol.", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.", "true");
		props.put("mail.smtp.port", "465");//port number 465 for Secure (SSL) and we can alsonuse port no 587 for Secure (TLS)
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);


		mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);	
		
	}
	
	/**
	 * Notify the employee by sending him a message.
	 * @param empl the employee to notify
	 * @param veh the vehicle the employee is trying to rent
	 * @param subject the subject of the notification
	 * @param message the message of the notification
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public void notifyEmployee(IEmployee empl, IVehicle veh, String subject, String message) throws RemoteException, AddressException, MessagingException {
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(this.notificationSenderEmail));
		InternetAddress[] address = {new InternetAddress(empl.getEmail())};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject);
		msg.setContent(message, "text/html");
		
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(host, this.notificationSenderEmail, this.notificationSenderPwd);


		try {
		    transport.sendMessage(msg, msg.getAllRecipients());
		    System.out.println("Send Success");
		   }

		catch (Exception err) {
			 System.out.println("ERROR in sending email process !!!!!!!    :(");
        }
        transport.close();
	}
	
	/**
	 * Called to return a car : add the note and condition of return to the vehicle and free it for next rental.
	 * If the waiting list of this car is not empty, rents the car to the first waiter and notifies him/her. 
	 * @param vehicleid the vehicle unique identifier
	 * @param note the note given by the employee returning the vehicle.
	 * @param conditionOfReturn a comment given by the employee to specify the condition of return of the vehicle.
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	@Override
	public void returnCar(long vehicleid, int note, String conditionOfReturn) throws RemoteException {
		List<IEmployee> rentersList = rentalWaitingList.get(Long.valueOf(vehicleid));
		
		// Removes the current employee renter from the rental list ; it corresponds to the first element of the list	
		rentersList.remove(0);
		
		IVehicle veh = park.get(Long.valueOf(vehicleid));
		veh.returnCar(note, conditionOfReturn);
		
		if (! rentersList.isEmpty()) {
			// Notify the first employee waiting for the car
			IEmployee empl = rentersList.get(0);
			
			veh.rentCar();
			
			String msg = "";
			try {
				msg = "Car number "+vehicleid+" has been returned. You are now rented it. Enjoy !!!!";
				System.out.println(" DANS RETURNCAR : "+msg);
				notifyEmployee(empl, veh, "IMPORTANT : Rental message", msg);
			} catch (MessagingException e) {
				System.out.println("ERROR IN EMPLOYEE NOTIFICATION ***");
				System.out.println("Unable to send an email with following message :");
				System.out.println(msg);
				
				e.printStackTrace();
			}
		}
	}

	/**
	 * Removes vehicle from the vehicles park because it is sold.
	 * @param vehId the vehicle identifier.
	 * @return 	true if the vehicle was successfully removed (thus sold)
	 * 			false if there is a problem (vehicle not already rented or not anymore in the park...)
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	@Override
	public boolean sellVehicle(long vehId) throws RemoteException {
		if (rentalWaitingList.remove(vehId) != null && park.remove(vehId) != null)
			return true;
		else return false;
	}
	
	/**
	 * Adds a new vehicle in the park given its attributes
	 * @param make the make of the vehicle
	 * @param model the model of the vehicle
	 * @param year the year of the vehicle
	 * @param seatingCapacity the number of seats of the vehicle
	 * @param fuelType the fuel type of the vehicle
	 * @param transmission the transmission of the vehicle
	 * @param priceInEuros the price of the vehicle
	 * @throws RemoteException may occur as this object will be used in remote method call.
	 */
	public void addVehicle(String make, String model, int year, int seatingCapacity, String fuelType, String transmission, float priceInEuros) throws RemoteException {
		Vehicle v = new Vehicle(this.vehicleCounterForUniqueId, make, model, year, seatingCapacity, fuelType, transmission, priceInEuros);

		park.put(Long.valueOf(this.vehicleCounterForUniqueId),v);
		
		rentalWaitingList.put(Long.valueOf(this.vehicleCounterForUniqueId), new ArrayList<IEmployee>());
		
		vehicleCounterForUniqueId++;
			
	}

}