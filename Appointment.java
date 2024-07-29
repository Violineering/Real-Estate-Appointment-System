package AppointmentSystem;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.*;
import java.util.Random;

public class Appointment {
	private String PropertyType;
	private String PropertyAddress;
	private String time;
	private String date;
	private String CustomerUsername;
	private int appointmentID;
	
	public Appointment(int appointmentID, String PropertyType, String PropertyAddress, String date, String time) {
		this.appointmentID = appointmentID;
		this.PropertyType = PropertyType;
		this.PropertyAddress = PropertyAddress;
		this.date = date;
		this.time = time;
	}
	
	 public int getAppointmentID() {
	        return appointmentID;
	    }

	    public void setAppointmentID(int appointmentID) {
	        this.appointmentID = appointmentID;
	    }

	    public String getPropertyType() {
	        return PropertyType;
	    }

	    public void setPropertyType(String PropertyType) {
	        this.PropertyType = PropertyType;
	    }

	    public String getPropertyAddress() {
	        return PropertyAddress;
	    }

	    public void setPropertyAddress(String PropertyAddress) {
	        this.PropertyAddress = PropertyAddress;
	    }

	    public String getDate() {
	        return date;
	    }

	    public void setDate(String date) {
	        this.date = date;
	    }

	    public String getTime() {
	        return time;
	    }

	    public void setTime(String time) {
	        this.time = time;
	    }

	@Override
	public String toString() {
		return "Appointment ID: " + appointmentID + ", Property type: " + PropertyType + ", Property address: "
				+ PropertyAddress + ", Date: " + date + ", Time: " + time;
	}

	// Method to convert appointment data to a string for file storage
	public String toFileString() {
		return appointmentID + ";" + PropertyType + ";" + PropertyAddress + ";" + date + ";" + time;
	}
	 // Method to convert appointment data to a string for UserAppointment file storage
	public String toUserAppointmentFileString() {
        return appointmentID + "," + CustomerUsername;
    }

	
	// Administrator and customer functions
	public static void Create_Appointment() {
		Random random = new Random();
		Scanner input = new Scanner(System.in);
		int choice;
		int appointmentID;
		String PropertyType = "";
		String PropertyAddress;
		String date;
		String time = "";

		MainPage.DateTime();
		System.out.println("--------------------------------------------------");
		System.out.println("\t\tCreate Appointment");
		System.out.println("--------------------------------------------------");
		System.out.println("");
		// Generate random appointment ID
		appointmentID = random.nextInt(10000) + 1;
		System.out.println("Random appointment ID generated: " + appointmentID);
		System.out.println("");
		// Ask administrator to choose the property type for appointment
		System.out.println("Please property fill in the property informations:");
		System.out.println("Choose a property type:");
		System.out.println("<1> Codominium");
		System.out.println("<2> Semi-Ds");
		System.out.println("<3> Terraced house");

		choice = input.nextInt();
		if (choice > 0 && choice < 4)
			switch (choice) {

			case 1:
				PropertyType = "Codominium";
				break;
			case 2:
				PropertyType = "Semi-Ds";
				break;
			case 3:
				PropertyType = "Terraced house";
				break;
			default:
				break;

			}
		else {
			System.out.println("INVALID CHOICE!!! Please chosse again.");
		}

		input.nextLine();
		// Ask administrator to input the property address
		System.out.println("Enter property address:");
		PropertyAddress = input.nextLine();

		// Ask administrator to input the date available for appointment
		System.out.println("Enter date available for booking (dd/mm/yy):");
		date = input.nextLine();

		// Ask administrator to choose the time available for appointment
		System.out.println("Enter time slot available for booking:");
		System.out.println("<1> 10:00-12:00");
		System.out.println("<2> 12:00-14:00");
		System.out.println("<3> 14:00-16:00");
		System.out.println("<4> 16:00-18:00");

		choice = input.nextInt();
		if (choice > 0 && choice < 5) {
			switch (choice) {

			case 1:
				time = "10:00-12:00";
				break;
			case 2:
				time = "12:00-14:00";
				break;
			case 3:
				time = "14:00-16:00";
				break;
			case 4:
				time = "16:00-18:00";
				break;
			default:
				break;

			}
		} else {
			System.out.println("INVALID CHOICE!!! Please chosse again.");
		}

		// Create an array to store the data input by administrator
		Appointment appointment = new Appointment(appointmentID, PropertyType, PropertyAddress, date, time);
		AppointmentManager appointmentManager = new AppointmentManager();

		// Save appointment to file
		appointmentManager.saveAppointmentsToFile("appointments.txt", appointment);
		
		// Ask the user to press Enter to main menu
        System.out.println("Please press Enter back to main menu...");      
        input.nextLine(); // Wait for Enter key
		return;
	}

	public static void Update_Appointment() {
		MainPage.DateTime();
		System.out.println("--------------------------------------------------");
		System.out.println("\t\tUpdate Appointment");
		System.out.println("--------------------------------------------------");

		Scanner input = new Scanner(System.in);
        AppointmentManager appointmentManager = new AppointmentManager();

        // Load appointments from file
        appointmentManager.loadAppointmentsFromFile("appointments.txt");

        // Prompt user for appointment ID to update
        System.out.print("Enter Appointment ID to update: ");
        int updateAppointmentID = input.nextInt();
        input.nextLine(); // Consume newline

        // Prompt user for update options
        System.out.println("Choose what to update (enter 'y' or 'n'):");
        System.out.print("Update Property Type? (y/n): ");
        String updatePropertyType = input.nextLine().toLowerCase();

        System.out.print("Update Property Address? (y/n): ");
        String updatePropertyAddress = input.nextLine().toLowerCase();

        System.out.print("Update Date? (y/n): ");
        String updateDate = input.nextLine().toLowerCase();

        System.out.print("Update Time? (y/n): ");
        String updateTime = input.nextLine().toLowerCase();

        // Prompt user for new information based on update options
        String newPropertyType = null;
        String newPropertyAddress = null;
        String newDate = null;
        String newTime = null;

        if (updatePropertyType.equals("y")) {
            System.out.print("Enter new Property Type: ");
            newPropertyType = input.nextLine();
        }
        if (updatePropertyAddress.equals("y")) {
            System.out.print("Enter new Property Address: ");
            newPropertyAddress = input.nextLine();
        }
        if (updateDate.equals("y")) {
            System.out.print("Enter new Date (YYYY-MM-DD): ");
            newDate = input.nextLine();
        }
        if (updateTime.equals("y")) {
            System.out.print("Enter new Time (HH:MM AM/PM): ");
            newTime = input.nextLine();
        }

        // Update appointment by ID with specified fields
        appointmentManager.updateAppointmentByID(updateAppointmentID, newPropertyType, newPropertyAddress, newDate, newTime);

        // Save updated appointments to file
        appointmentManager.saveAppointmentsToFile("appointments.txt");
        
        // Ask the user to press Enter to main menu
        System.out.println("Please press Enter back to main menu...");      
        input.nextLine(); // Wait for Enter key
		return;
	}

	public static void View_Appointment() {
		MainPage.DateTime();
		Scanner input = new Scanner(System.in);
		AppointmentManager appointmentManager = new AppointmentManager();
		System.out.println("--------------------------------------------------");
		System.out.println("\t\tView Appointment");
		System.out.println("--------------------------------------------------");

		// Load appointments from file
        appointmentManager.loadAppointmentsFromFile("appointments.txt");
        
		// View all appointments
        appointmentManager.viewAppointments();
        
        // Ask the user to press Enter to main menu
        System.out.println("Please press Enter back to main menu...");      
        input.nextLine(); // Wait for Enter key
		return;
	}

	public static void Delete_Appointment() {
		MainPage.DateTime();
		System.out.println("--------------------------------------------------");
		System.out.println("\t\tCancel Appointment");
		System.out.println("--------------------------------------------------");
		Scanner input = new Scanner(System.in);
		
        AppointmentManager appointmentManager = new AppointmentManager();

        // Load appointments from file
        appointmentManager.loadAppointmentsFromFile("appointments.txt");

        // Prompt user for appointment ID to delete
        System.out.print("Enter Appointment ID to delete: ");
        int deleteAppointmentID = input.nextInt();
        input.nextLine(); // Consume newline

        // Delete appointments by ID
        appointmentManager.deleteAppointmentByID(deleteAppointmentID);

        // Save updated appointments to file
        appointmentManager.saveAppointmentsToFile("appointments.txt");
    
        // Ask the user to press Enter to main menu
        System.out.println("Please press Enter back to main menu...");      
        input.nextLine(); // Wait for Enter key
		return;
	}

	public static void Search_Appointment() {
		Scanner input = new Scanner(System.in);
		AppointmentManager appointmentManager = new AppointmentManager();
		// Load appointments from file
        appointmentManager.loadAppointmentsFromFile("appointments.txt");

        // Prompt user for appointment ID to search
        System.out.print("Enter Appointment ID to search: ");
        int searchAppointmentID = input.nextInt();
        input.nextLine(); // Consume newline

        // Find appointment by ID
        Appointment foundAppointment = appointmentManager.findAppointmentByID(searchAppointmentID);

        // Display appointment details if found
        if (foundAppointment != null) {
            System.out.println("Appointment found.");
            System.out.println("");
            System.out.println("Property Type: " + foundAppointment.getPropertyType());
            System.out.println("Property Address: " + foundAppointment.getPropertyAddress());
            System.out.println("Date: " + foundAppointment.getDate());
            System.out.println("Time: " + foundAppointment.getTime());
        } else {
            System.out.println("Appointment with ID " + searchAppointmentID + " not found.");
        }
        
        // Ask the user to press Enter to main menu
        System.out.println("Please press Enter back to main menu...");      
        input.nextLine(); // Wait for Enter key
    }
	
}

	class AppointmentManager {
	// create an array list for the appointments
	public ArrayList<Appointment> appointments;

	public AppointmentManager() {
		this.appointments = new ArrayList<>();
	}

	// Method to save appointments to a file
	public void saveAppointmentsToFile(String filename, Appointment newAppointment) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename, true))) {
			writer.println(newAppointment.toFileString());
			System.out.println("Appointment saved to file: " + filename);
		} catch (IOException e) {
			System.out.println("Error saving appointment to file: " + e.getMessage());
		}
	}

	// Method to load appointments from a file
	public void loadAppointmentsFromFile(String filename) {
		appointments.clear(); // Clear existing appointments
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(";");
				int appointmentID = Integer.parseInt(parts[0]);
				String propertyType = parts[1];
				String propertyAddress = parts[2];
				String date = parts[3];
				String time = parts[4];
				appointments.add(new Appointment(appointmentID, propertyType, propertyAddress, date, time));
			}
			System.out.println("Appointments loaded from file: " + filename);
		} catch (IOException e) {
			System.out.println("Error loading appointments from file: " + e.getMessage());
		}
	}
	
	public Appointment findAppointmentByID(int appointmentID) {
        for (Appointment appointment : appointments) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null; // Return null if appointment with given ID is not found
    }
	
	 public void viewAppointments() {
	        System.out.println("All Appointments:");
	        for (Appointment appointment : appointments) {
	            System.out.println(appointment);
	        }
	    }
	 
	// Method to delete appointments by ID
	    public void deleteAppointmentByID(int appointmentID) {
	        Iterator<Appointment> iterator = appointments.iterator();
	        while (iterator.hasNext()) {
	            Appointment appointment = iterator.next();
	            if (appointment.getAppointmentID() == appointmentID) {
	                iterator.remove();
	                System.out.println("Appointment with ID " + appointmentID + " deleted successfully.");
	            }
	        }
	    }
	    
	    public void saveAppointmentsToFile(String filename) {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
	            for (Appointment appointment : appointments) {
	                writer.println(appointment.toFileString());
	            }
	            System.out.println("Appointments saved to file: " + filename);
	        } catch (IOException e) {
	            System.out.println("Error saving appointments to file: " + e.getMessage());
	        }
	    }
	    
	    public void updateAppointmentByID(int appointmentID, String newPropertyType, String newPropertyAddress, String newDate, String newTime) {
	        for (Appointment appointment : appointments) {
	            if (appointment.getAppointmentID() == appointmentID) {
	                if (newPropertyType != null) {
	                    appointment.setPropertyType(newPropertyType);
	                }
	                if (newPropertyAddress != null) {
	                    appointment.setPropertyAddress(newPropertyAddress);
	                }
	                if (newDate != null) {
	                    appointment.setDate(newDate);
	                }
	                if (newTime != null) {
	                    appointment.setTime(newTime);
	                }
	                System.out.println("Appointment with ID " + appointmentID + " updated successfully.");
	                return;
	            }
	        }
	        System.out.println("Appointment with ID " + appointmentID + " not found.");
	    }
	    
	 
}

