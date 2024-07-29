package AppointmentSystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CustomerAppointment {
	private int appointmentID;
	private String username; // New field to store the username of the user making the appointment

	public CustomerAppointment(int appointmentID, String username) {
		this.appointmentID = appointmentID;
		this.username = username;
	}

	// Getters and setters
	public int getAppointmentID() {
		return appointmentID;
	}

	public void setAppointmentID(int appointmentID) {
		this.appointmentID = appointmentID;
	}

	public String getusername() {
		return username;
	}

	public void setPropertyType(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Appointment ID: " + appointmentID + ", Username: " + username;
	}

	// Method to convert appointment data to a string for file storage
	public String toFileString() {
		return appointmentID + ";" + username;
	}

	public static void Make_Appointment(String Username) {
		MainPage.DateTime();
		AppointmentManager appointmentManager = new AppointmentManager();
		CustomerAppointmentManager Customer_appointmentManager = new CustomerAppointmentManager();
		Scanner input = new Scanner(System.in);
		String user = Username;
		System.out.println("--------------------------------------------------");
		System.out.println("\t\tMake an appointment");
		System.out.println("--------------------------------------------------");

		// Load appointments from appointments.txt file
		appointmentManager.loadAppointmentsFromFile("appointments.txt");

		System.out.println("Property available for booking:");
		// View all appointments in appointments.txt file
		appointmentManager.viewAppointments();
		System.out.println("\nPlease enter a appointment ID from the list for booking:");
		int appointmentID = input.nextInt();

		// Make an appointment
		Customer_appointmentManager.makeAppointment(appointmentID, user);

		// Create an array to store the data input by administrator
		CustomerAppointment appointment = new CustomerAppointment(appointmentID, user);

		// Save appointments to UserAppointment.txt file
		Customer_appointmentManager.saveAppointmentsToFile("UserAppointment.txt", appointment);
		System.out.println("\nAppointment with ID " + appointmentID + " is made by " + user);
		System.out.println("Press Enter to continue...");
		input.nextLine();
		input.nextLine();

	}

	public static void View_Appointment_Made(String Username) {
		Scanner input = new Scanner(System.in);
		String user = Username;
		CustomerAppointmentManager Customer_appointmentManager = new CustomerAppointmentManager();
		 // Load appointments from UserAppointment.txt file
	    System.out.println("");
	    Customer_appointmentManager.loadAppointmentsFromFile("UserAppointment.txt");
	    System.out.println("Below are all the appointments made by: " + user);
	    // View appointments made by the specified user
	    System.out.println("");
	    Customer_appointmentManager.viewCustomerAppointments(user);

		System.out.println("\nTo view the details of appointment made, please enter the appointment ID at <Search Appointment>.");
		System.out.println("Press Enter to continue...");
		input.nextLine();

	}

	public static void Cancel_Appointment(String Username) {
	    Scanner input = new Scanner(System.in);
	    String user = Username;
	    CustomerAppointmentManager Customer_appointmentManager = new CustomerAppointmentManager();
	    // Load appointments from UserAppointment.txt file
	    System.out.println("");
	    Customer_appointmentManager.loadAppointmentsFromFile("UserAppointment.txt");
	    System.out.println("Below is all the appointment made by: " + user);
	    // View all appointments in appointments.txt file
	    System.out.println("");
	    
	    // Check if there are appointments for the specified user
	    if(Customer_appointmentManager.hasAppointmentsForUser(user)) {
	        Customer_appointmentManager.viewCustomerAppointments(user);
	        
	        // ask the user to input appointment ID that the user wish to cancel the appointment
	        System.out.println("Please enter the appointment ID that you wish to cancel: ");
	        int cancelAppointment = input.nextInt();
	        input.nextLine(); // Consume newline

	        // call the cancel appointment method to cancel the appointment with appointment ID input by customer
	        Customer_appointmentManager.cancelCustomerAppointmentByID(cancelAppointment);

	        // Save appointments to UserAppointment.txt file
	        Customer_appointmentManager.saveAppointmentsToFile("UserAppointment.txt");
	    } else {
	        System.out.println("No appointments available for cancellation.");
	    }
	    
	    System.out.println("Please press Enter to continue...");
	    input.nextLine();
	}
}

class CustomerAppointmentManager {
	private ArrayList<CustomerAppointment> CustomerAppointments;

	public CustomerAppointmentManager() {
		this.CustomerAppointments = new ArrayList<>();
	}

	// Method to make an appointment
	public void makeAppointment(int appointmentID, String username) {
		CustomerAppointment newCustomerAppointment = new CustomerAppointment(appointmentID, username);
		CustomerAppointments.add(newCustomerAppointment);
	}

	// Method to save appointments to a file
	public void saveAppointmentsToFile(String filename) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			for (CustomerAppointment appointment : CustomerAppointments) {
				writer.println(appointment.toFileString());
			}
			System.out.println("Appointments saved to file: " + filename);
		} catch (IOException e) {
			System.out.println("Error saving appointments to file: " + e.getMessage());
		}
	}

	// Method to save appointments to a file
	public void saveAppointmentsToFile(String filename, CustomerAppointment newCustomerAppointment) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
			writer.println(newCustomerAppointment.toFileString());
			System.out.println("Appointment saved to file: " + filename);
		} catch (IOException e) {
			System.out.println("Error saving appointment to file: " + e.getMessage());
		}
	}

	// Method to load appointments from a file
	public void loadAppointmentsFromFile(String filename) {
		CustomerAppointments.clear(); // Clear existing appointments
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				int appointmentID = Integer.parseInt(parts[0]);
				String username = parts[1];
				CustomerAppointments.add(new CustomerAppointment(appointmentID, username));
			}
			System.out.println("Appointments loaded from file: " + filename);
		} catch (IOException e) {
			System.out.println("Error loading appointments from file: " + e.getMessage());
		}
	}

	public void viewCustomerAppointments(String username) {
	    System.out.println("Appointments for User: " + username);
	    boolean foundAppointments = false;
	    for (CustomerAppointment appointment : CustomerAppointments) {
	        if (appointment.getusername().equals(username)) {
	            System.out.println(appointment);
	            foundAppointments = true;
	        }
	    }
	    if (!foundAppointments) {
	        System.out.println("No appointments found for user: " + username);
	    }
	}

	// Method to delete appointments by ID
	public void cancelCustomerAppointmentByID(int appointmentID) {
		Iterator<CustomerAppointment> iterator = CustomerAppointments.iterator();
		while (iterator.hasNext()) {
			CustomerAppointment appointment = iterator.next();
			if (appointment.getAppointmentID() == appointmentID) {
				iterator.remove();
				System.out.println("Appointment with ID " + appointmentID + " cancelled successfully.");
			}
		}
	}
	
	public boolean hasAppointmentsForUser(String username) {
	    for (CustomerAppointment appointment : CustomerAppointments) {
	        if (appointment.getusername().equals(username)) {
	            return true;
	        }
	    }
	    return false;
	}
}
