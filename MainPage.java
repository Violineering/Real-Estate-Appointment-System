package AppointmentSystem;

import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.regex.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainPage {

	private static int currentUserIndex;
	private static Users user;
	// include Administrator data file
	private static final String USERS_DATA_FILE = "Users_data.txt";
	// Put administrator data into an array list
	private static ArrayList<String> users = new ArrayList<>();
	private static ArrayList<Users> UsersList = new ArrayList<>();

	public static void main(String[] args) {

		user = new Users("", "", "", "");
		Scanner input = new Scanner(System.in);

		int choice;
		do {
			System.out.println("---------------------------------------------------------------");
			System.out.println("\t\t REAL ESTATE APPOINTMENT SYSTEM");
			System.out.println("---------------------------------------------------------------");
			System.out.println("Log In Interface:");
			System.out.println("1. Administrator");
			System.out.println("2. Customer");
			System.out.println("3. Create User");
			System.out.println("4. EXIT");

			System.out.println("Enter your choice:");
			choice = input.nextInt();

			switch (choice) {

			case 1:
				Administrator();
				break;
			case 2:
				Customer();
				break;
			case 3:
				Create_User();
				break;
			case 4:
				System.out.println("Exiting...");
				break;
			default:
				System.out.println("INVALID CHOICE!!! Please chosse again.");
				break;

			}
		} while (choice != 4);

	}

	public static void Administrator() {
		boolean login = false;
		Scanner input = new Scanner(System.in);

		do {
			LoadUsersData();
			DateTime();
			System.out.println("---------------------------------------------------------------");
			System.out.println("\t\t Log In for Administrator");
			System.out.println("---------------------------------------------------------------");

			// Let Administrator key in username and password to log in
			System.out.println("Please enter your username: ");
			String Username = input.nextLine();
		
			// check validity of the username, if the username is valid then proceed to key
			// in password
			if (isValidUser(Username)) {
				System.out.print("Enter your password: ");
				String UserPassword = input.nextLine();

				// check validity of password
				if (isPasswordValid(Username, UserPassword)) {
					System.out.println("Login successful!\n");
					user.setUsername(Username);
					login = true;
				}

				// if the password is wrong ask the user to try again.
				else
					System.out.println("Invalid password. Try again.\n");
			}

			// if username is incorrect ask user to key in a valid username.
			else {
				System.out.println("Invalid username. Please enter a valid username. \n");
			}
		} while (login != true);

		// Options display
		char option;
		do {
			DateTime();
			System.out.println("Welcome " + user.getUsername());
			System.out.println("                                                 ");
			System.out.println("Please choose an action:");
			System.out.println("<1> Create Appointment");
			System.out.println("<2> Update Appointment");
			System.out.println("<3> View Appointment");
			System.out.println("<4> Delete Appointment");
			System.out.println("<5> Search Appointment");
			System.out.println("<6> Edit profile");
			System.out.println("<7> Back to log in page");
			System.out.println("-------------------------------------------");
			System.out.print("Enter an option \t>> ");
			option = input.next().charAt(0);

			switch (option) {
			case '1':
				Appointment.Create_Appointment();
				break;
			case '2':
				Appointment.Update_Appointment();
				break;
			case '3':
				Appointment.View_Appointment();
				break;
			case '4':
				Appointment.Delete_Appointment();
				break;
			case '5':
				Appointment.Search_Appointment();
				break;
			case '6':
				Edit_profile();
				break;
			case '7':
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid option. Enter option again. ");
				break;
			}
			System.out.println();
		} while (option != '7');
		input.close();

	}

	public static void Customer() {
		boolean login = false;
		Scanner input = new Scanner(System.in);

		do {
			LoadUsersData();
			System.out.println("---------------------------------------------------------------");
			System.out.println("\t\t Log In for Customer");
			System.out.println("---------------------------------------------------------------");

			// Let Administrator key in username and password to log in
			System.out.println("Please enter your username: ");
			String Username = input.nextLine();

			// check validity of the username, if the username is valid then proceed to key
			// in password
			if (isValidUser(Username)) {
				System.out.print("Enter your password: ");
				String UserPassword = input.nextLine();

				// check validity of password
				if (isPasswordValid(Username, UserPassword)) {
					System.out.println("Login successful!\n");
					user.setUsername(Username);
					login = true;
				}

				// if the password is wrong ask the user to try again.
				else
					System.out.println("Invalid password. Try again.\n");
			}

			// if username is incorrect ask user to key in a valid username.
			else {
				System.out.println("Invalid username. Please enter a valid username. \n");
			}
		} while (login != true);

		// Options display
		char option;
		do {
			DateTime();
			System.out.println("Welcome " + user.getUsername());
			System.out.println("");
			System.out.println("Please choose an action:");
			System.out.println("<1> Make an Appointment");
			System.out.println("<2> View all available Appointment");
			System.out.println("<3> View Appointment made");
			System.out.println("<4> Cancel Appointment made");
			System.out.println("<5> Search Appointment");
			System.out.println("<6> Edit profile");
			System.out.println("<7> Back to log in page");
			System.out.println("-------------------------------------------");
			System.out.print("Enter an option \t>> ");
			option = input.next().charAt(0);

			switch (option) {
			case '1':
				CustomerAppointment.Make_Appointment(user.getUsername());
				break;
			case '2':
				Appointment.View_Appointment();
				break;
			case '3':
				CustomerAppointment.View_Appointment_Made(user.getUsername());
				break;
			case '4':
				CustomerAppointment.Cancel_Appointment(user.getUsername());
				break;
			case '5':
				Appointment.Search_Appointment();
				break;
			case '6':
				Edit_profile();
				break;
			case '7':
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid option. Enter option again. ");
				break;
			}
			System.out.println();
		} while (option != '7');
		input.close();
	}

	public static void Create_User() {
		Scanner input = new Scanner(System.in);
		System.out.println("---------------------------------------------------------------");
		System.out.println("\t\t Register a new user");
		System.out.println("---------------------------------------------------------------");
		System.out.println("Enter Username: ");
		String username = input.nextLine();
		if (!username.equals("")) {
			user.setUsername(username);
		}

		System.out.println("Enter Password: ");
		String password = input.nextLine();
		if (!password.equals("")) {
			user.setUsername(password);
		}

		boolean valid;
		valid = true;
		while (valid) {
			System.out.println("Enter E-mail: ");
			String email = input.nextLine();

			// Regular Expression
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
			// Compile regular expression to get the pattern
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(email);
			if (matcher.matches() || email.equals("")) {
				valid = false;
				if (!email.equals("")) {
					user.setEmail(email);
				}
			} else {
				System.out.print("Wrong email format. Please re-enter the email.\n");
			}
		}

		valid = true;
		while (valid) {
			System.out.println("");
			System.out.println("Enter new Phone number (***-{7/8 digits}): ");
			String PhoneNo = input.nextLine();

			// Regular Expression
			String regex = "^(01\\d-\\d{7}|01\\d-\\d{8})$";
			// Compile regular expression to get the pattern
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(PhoneNo);
			if (matcher.matches() || PhoneNo.equals("")) {
				valid = false;
				if (!PhoneNo.equals("")) {
					user.setPhoneNo(PhoneNo);
				}
			} else {
				System.out.print("Wrong Phone number format. Please re-enter the Phone number.\n");
			}

		}
		System.out.println("\nCurrent user profile information:");
		System.out.println("Username: " + username);
		System.out.println("Password: " + password);
		System.out.println("Email: " + user.getEmail());
		System.out.println("Phone number: " + user.getPhoneNo());
		// Create a new user
		createNewUser(username, password, user.getEmail(), user.getPhoneNo());

		// Save users to file
		saveUsersToFile("Users_data.txt");
		System.out.println("\nUser " + username + " created successfully.");
		System.out.println("Press Enter to back to log in page...");
		input.nextLine();

	}

	// Read users data
	private static void LoadUsersData() {
		users = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(USERS_DATA_FILE))) {
			String line;
			while ((line = reader.readLine()) != null) {
				users.add(line);
			}
		} catch (IOException e) {
			System.err.println("Error reading user data file: " + e.getMessage());
		}
	}

	public static void DateTime() {
		System.out.println("Date: \t\t\t\t\t Time:");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(("dd/MM/yyyy \t\t\t\t HH:mm:ss"));
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dtf.format(now));
	}

	private static ArrayList<Users> loadUsersList() {
		ArrayList<Users> Userl = new ArrayList<Users>();
		for (int i = 0; i < users.size(); i++) {
			String[] UsersSplit = users.get(i).split(";");
			Users user = new Users(UsersSplit[0], UsersSplit[1], UsersSplit[2], UsersSplit[3]);
			Userl.add(user);
		}
		return Userl;
	}

	private static int loadcurrentUserIndex(String Username) {
		for (int i = 0; i < UsersList.size(); i++) {
			if (UsersList.get(i).getUsername().equals(Username)) {
				return i;
			}
		}
		return -1; // throw Error
	}

	private static void writeUserData() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_DATA_FILE, false))) {
			for (int i = 0; i < users.size(); i++) {
				if (i == currentUserIndex) {
					writer.write(UsersList.get(i).getFileString());
				} else {
					writer.write(users.get(i));
				}
				writer.newLine();
			}
			System.out.println("User updated successfully.");
		} catch (IOException e) {
			System.err.println("Error updating a user: " + e.getMessage());
		}
	}

	// Method to create a new user
	public static void createNewUser(String username, String password, String email, String PhoneNo) {
		Users newUser = new Users(username, password, email, PhoneNo);
		UsersList.add(newUser);
	}

	// Method to save users to a file
	public static void saveUsersToFile(String filename) {
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			for (Users user : UsersList) {
				writer.println(user.getFileString());
			}
			System.out.println("Users saved to file: " + filename);
		} catch (IOException e) {
			System.out.println("Error saving users to file: " + e.getMessage());
		}
	}

	// check validity of administrator username
	private static boolean isValidUser(String Username) {
		for (String user : users) {
			String[] parts = user.split(";");
			if (parts.length == 4 && parts[0].equals(Username)) {
				return true;
			}
		}
		return false;
	}

	// check administrator password validity
	private static boolean isPasswordValid(String Username, String Password) {
		for (String user : users) {
			String[] parts = user.split(";");
			if (parts.length == 4 && parts[0].equals(Username) && parts[1].equals(Password)) {
				return true;
			}
		}
		return false;
	}

	private static void Edit_profile() {
		boolean valid;
		String username = user.getUsername();
		LoadUsersData();
		UsersList = loadUsersList();
		currentUserIndex = loadcurrentUserIndex(username);
		valid = false;

		// Create a sample user
		Users SampleUser = UsersList.get(currentUserIndex);

		// Create a Scanner object for user input
		Scanner input = new Scanner(System.in);

		// Display the current user profile information
		System.out.println("Current User Profile Information:");
		System.out.println("Username: " + SampleUser.getUsername());
		System.out.println("Email: " + SampleUser.getEmail());
		System.out.println("Phone number: " + SampleUser.getPhoneNo());

		// Prompt the user to update their profile information
		System.out.println("\nUpdate User Profile Information, press Enter to continue...");
		input.nextLine();
		System.out.println("Please press enter to continue if you do not wish to update your Username.");
		System.out.println("Enter new Username: ");
		String newUsername = input.nextLine();

		if (!newUsername.equals("")) {
			// Update the username in Users_data.txt
			users.set(currentUserIndex, newUsername + ";" + SampleUser.getPassword() + ";" + SampleUser.getEmail() + ";"
					+ SampleUser.getPhoneNo());

			// Set the new username in the User object
			SampleUser.setUsername(newUsername);
			// Update the user object with the new username
			user.setUsername(newUsername);
			System.out.println("Username updated successfully.");
		}
		System.out.println("");
		System.out.println("Please press enter if you do not wish to update your password.");
		System.out.println("Enter new password: ");
		String newPassword = input.nextLine();
		if (!newPassword.equals("")) {
			// Update the username in Users_data.txt
			users.set(currentUserIndex,
					newUsername + ";" + newPassword + ";" + SampleUser.getEmail() + ";" + SampleUser.getPhoneNo());

			// Set the new username in the User object
			SampleUser.setPassword(newPassword);
			// Update the user object with the new username
			user.setPassword(newPassword);
			System.out.println("Password updated successfully.");
		}

		valid = true;
		while (valid) {
			System.out.println("");
			System.out.println("Please press enter if you do not wish to update your email.");
			System.out.println("Enter new email: ");
			String newEmail = input.nextLine();

			// Regular Expression
			String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
			// Compile regular expression to get the pattern
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(newEmail);
			if (matcher.matches() || newEmail.equals("")) {
				valid = false;
				if (!newEmail.equals("")) {
					// Update the username in Users_data.txt
					users.set(currentUserIndex, newUsername + ";" + SampleUser.getPassword() + ";" + newEmail + ";"
							+ SampleUser.getPhoneNo());
					// Set the new username in the User object
					SampleUser.setEmail(newEmail);
					// Update the user object with the new email
					user.setEmail(newEmail);
					System.out.println("Email updated successfully.");
				}
			} else {
				System.out.print("Wrong email format. Please re-enter the email.\n");
			}
		}

		valid = true;
		while (valid) {
			System.out.println("");
			System.out.println("Please press enter if you do not wish to update your phone number.");
			System.out.println("Enter new Phone number (***-{7/8 digits}): ");
			String newPhoneNo = input.nextLine();

			// Regular Expression
			String regex = "^(01\\d-\\d{7}|01\\d-\\d{8})$";
			// Compile regular expression to get the pattern
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(newPhoneNo);
			if (matcher.matches() || newPhoneNo.equals("")) {
				valid = false;
				if (!newPhoneNo.equals("")) {
					// Update the username in Users_data.txt
					users.set(currentUserIndex, newUsername + ";" + SampleUser.getPassword() + ";"
							+ SampleUser.getEmail() + ";" + newPhoneNo);
					// Set the new username in the User object
					SampleUser.setPhoneNo(newPhoneNo);
					// Update the user object with the new email
					user.setPhoneNo(newPhoneNo);
					System.out.println("Phone number updated successfully.");
				}
			} else {
				System.out.print("Wrong Phone number format. Please re-enter the Phone number.\n");
			}
		}

		// Display the updated user profile information
		System.out.println("\nUser profile information updated successfully!");
		System.out.println("Current user profile information:");
		System.out.println("Username: " + SampleUser.getUsername());
		System.out.println("Password: " + SampleUser.getPassword());
		System.out.println("Email: " + SampleUser.getEmail());
		System.out.println("Phone number: " + SampleUser.getPhoneNo());

		// Update the user data in user_data.txt
		writeUserData();

		// Ask the user to press Enter to continue
		System.out.println("Please press Enter to continue...");

		input.nextLine(); // Wait for Enter key
		return;
	}
}
