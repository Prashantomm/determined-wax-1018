package com.masai.ui;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.masai.admin.Admin;
import com.masai.color.Color;
import com.masai.entity.Booking;
import com.masai.entity.Customer;
import com.masai.entity.Home;
import com.masai.entity.Payment;
import com.masai.exceptions.NoHomeRecordFoundException;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;
import com.masai.service.BookingService;
import com.masai.service.BookingServiceImpl;
import com.masai.service.CustomerService;
import com.masai.service.CustomerServiceImpl;
import com.masai.service.HomeService;
import com.masai.service.HomeServiceImpl;
import com.masai.service.PaymentService;
import com.masai.service.PaymentServiceImpl;

public class AdminUI {
	static void displayAdminMenu() {
		System.out.println("╔═══════════════ Admin Menu ══════════════╗");
		System.out.println("║ 1. Add Homes                            ║");
		System.out.println("║ 2. Update Home                          ║");
		System.out.println("║ 3. Delete Homes                         ║");
		System.out.println("║ 4. Get All Homes                        ║");
		System.out.println("║ 5. Add Customer                         ║");
		System.out.println("║ 6. Update Customer                      ║");
		System.out.println("║ 7. Delete Customer                      ║");
		System.out.println("║ 8. Get All Customers                    ║");
		System.out.println("║ 9. Get Customer By ID                   ║");
		System.out.println("║ 10. Get Customer By Username            ║");
		System.out.println("║ 11. Get All Payments                    ║");
		System.out.println("║ 12. Get All Bookings                    ║");
		System.out.println("║ 13. Get Booking Between Dates           ║");
		System.out.println("║ 0. Logout                               ║");
		System.out.println("╚═════════════════════════════════════════╝");
	}

	
	static void adminMenu(Scanner sc)
			throws SomethingWentWrongException, NoRecordFoundException, NoHomeRecordFoundException {
		int choice = 0;
		do {
			displayAdminMenu();
			System.out.print(Color.GREEN+"Enter selection: "+Color.RESET);
			choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {
			case 1:
				addHome(sc);
				break;
			case 2:
				updateHome(sc);
				break;
			case 3:
				deleteHome(sc);
				break;
			case 4:
				getAllHomes();
				break;
			case 5:
				addCustomer(sc);
				break;
			case 6:
				updateCustomer(sc);
				break;
			case 7:
				deleteCustomer(sc);
				break;
			case 8:
				getAllCustomers();
				break;
			case 9:
				getCustomerById(sc);
				break;
			case 10:
				getCustomerByUsername(sc);
				break;
			
			case 11:
				getAllPayments();
				break;
			case 12:
				getAllBookings();
				break;
			case 13:
				getBookingsBetweenDates(sc);
				break;
			case 0:
				System.out.println(Color.FOREST_GREEN_BACKGROUND+"Logout Successfully"+Color.RESET);
				break;
			default:
				System.out.println(Color.RED_BACKGROUND+"Invalid Selection, try again"+Color.RESET);
			}
		} while (choice != 0);
	}

	
	public static void printHomeDetails(Home home) {
		System.out.println("--------------------------------------------------");
		System.out.println("Home Details:");
		System.out.println("ID: " + home.getHomeId());
		System.out.println("Area: " + home.getArea());
		System.out.println("Type: " + home.getType());
		System.out.println("Price: " + home.getPrice());
		System.out.println("Availability: " + (home.isAvailability() ? "Available" : "Not Available"));
		System.out.println("Deleted: " + (home.isDeleted() ? "Yes" : "No"));
		System.out.println("--------------------------------------------------");
	}

	
	public static void printCustomerDetails(Customer customer) {
		if (customer != null) {
			System.out.println("----------------------------");
			System.out.println("Customer Details:");
			System.out.println("ID: " + customer.getCustomerId());
			System.out.println("First Name: " + customer.getFirstName());
			System.out.println("Last Name: " + customer.getLastName());
			System.out.println("Email: " + customer.getEmail());
			System.out.println("Phone Number: " + customer.getPhoneNumber());
			System.out.println("Username: " + customer.getUsername());
			 //Do not print the password for security reasons
			System.out.println("----------------------------");
		} else {
			System.out.println("Customer not found.");
		}
	}

	
	private static void addHome(Scanner sc) throws SomethingWentWrongException {
		System.out.print("Enter Home Location: ");
		String loca = sc.nextLine();

		System.out.print("Enter Home type(1BHK/2BHK/3BHK/4BHK): ");
		String type = sc.nextLine();

		System.out.print("Enter Home Price: ");
		double price = sc.nextDouble();

		System.out.print("Is the Home Available (true/false): ");
		boolean availability = sc.nextBoolean();

		System.out.print("Is the Home Deleted (true/false): ");
		boolean isDeleted = sc.nextBoolean();

		Home home = new Home(loca, type, price, availability, isDeleted);
		HomeService homeService = new HomeServiceImpl();
		try {
			Home h2 =homeService.addHome(home);
			if (h2 != null) {
				System.out.println(Color.GREEN_BACKGROUND+"Home details added successfully."+Color.RESET);
				printHomeDetails(home);
			}
		} catch (SomethingWentWrongException e) {
			throw new SomethingWentWrongException("Something went wrong while adding the home inside the System.");
		}
	}

	
	private static void updateHome(Scanner sc) throws SomethingWentWrongException, NoRecordFoundException, NoHomeRecordFoundException {
		System.out.print("Enter Home ID to update: ");
		long homeId = sc.nextLong();
		sc.nextLine();

		System.out.print("Enter Home Location: ");
		String loca = sc.nextLine();

		System.out.print("Enter Home Type: ");
		String type = sc.nextLine();

		System.out.print("Enter Home Price: ");
		double price = sc.nextDouble();

		System.out.print("Is the Home Available (true/false): ");
		boolean availability = sc.nextBoolean();

		System.out.print("Is the Home Deleted (true/false): ");
		boolean isDeleted = sc.nextBoolean();

		Home home= new Home(loca, type, price, availability, isDeleted);
		home.setHomeId(homeId);
		HomeService homeService = new HomeServiceImpl();
		try {
			Home h2 = homeService.updateHome(home);
			if (h2 != null) {
				System.out.println(Color.GREEN_BACKGROUND+"Home details Updated successfully."+Color.RESET);
				printHomeDetails(home);
			}
		} catch (SomethingWentWrongException e) {
			throw new SomethingWentWrongException ("Home was not fount in the System.");
		}
	}

	
	private static void deleteHome(Scanner sc) throws NoRecordFoundException {
		System.out.print("Enter Home ID to delete: ");
		long homeId = sc.nextLong();
		HomeService homeService = new HomeServiceImpl();
		try {
			homeService.deleteHome(homeId);
			System.out.println("------------------------------------------");
			System.out.println(Color.GREEN_BACKGROUND+"Home has been deleted from the system."+Color.RESET);
			System.out.println("------------------------------------------");
		} catch (SomethingWentWrongException e) {
			throw new NoRecordFoundException("home was not fount in the System.");
		}
	}

	
	public static void getAllHomes() throws SomethingWentWrongException {
		HomeService homeService = new HomeServiceImpl();
		try {
			List<Home> list = homeService.getAllHomes();
			for (Home h : list) {
				printHomeDetails(h);
			}
		} catch (Exception e) {
			throw new SomethingWentWrongException("Some thing went wrong, Try again later.");
		}
	}

	

	public static void addCustomer(Scanner sc) {

		System.out.print("Enter First Name: ");
		String firstName = sc.nextLine();
		
		System.out.print("Enter Last Name: ");
		String lastName = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.nextLine();

		System.out.print("Enter Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Password: ");
		String password = sc.nextLine();

		Customer customer = new Customer(firstName, lastName, email, phoneNumber, username, password);

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.addCustomer(customer);
			System.out.println(Color.GREEN_BACKGROUND+"Customer Added successfully."+Color.RESET);
			System.out.println();
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void updateCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to update: ");
		long customerId = sc.nextLong();
		sc.nextLine();

		System.out.print("Enter First Name: ");
		String firstName = sc.nextLine();

		System.out.print("Enter Last Name: ");
		String lastName = sc.nextLine();

		System.out.print("Enter Email: ");
		String email = sc.nextLine();

		System.out.print("Enter Phone Number: ");
		String phoneNumber = sc.nextLine();

		System.out.print("Enter Username: ");
		String username = sc.nextLine();

		System.out.print("Enter Password: ");
		String password = sc.nextLine();

		Customer customer = new Customer(firstName, lastName, email, phoneNumber, username, password);
		customer.setCustomerId(customerId);

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.updateCustomer(customer);
			System.out.println(Color.GREEN_BACKGROUND+"Customer updated successfully."+Color.RESET);
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	
	private static void deleteCustomer(Scanner sc) {
		System.out.print("Enter Customer ID to delete: ");
		long customerId = sc.nextLong();

		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.deleteCustomer(customerId);
			System.out.println(Color.GREEN_BACKGROUND+"Customer Deleted Successfully."+Color.RESET);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getAllCustomers() {
		CustomerService cs = new CustomerServiceImpl();
		try {
			List<Customer> list = cs.getAllCustomers();
			for (Customer c1 : list) {
				printCustomerDetails(c1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getCustomerById(Scanner sc) {
		System.out.print("Enter Customer ID to get details: ");
		long customerId = sc.nextLong();

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.getCustomerById(customerId);
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getCustomerByUsername(Scanner sc) {
		System.out.print("Enter Customer Username to get details: ");
		String username = sc.next();

		CustomerService cs = new CustomerServiceImpl();
		try {
			Customer c1 = cs.getCustomerByUsername(username);
			printCustomerDetails(c1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getAllPayments() {
		PaymentService ps = new PaymentServiceImpl();
		try {
			List<Payment> payments = ps.getAllPayments();
			payments.forEach(p -> CustomerUI.generateReceipt(p));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getAllBookings() {
		BookingService bs = new BookingServiceImpl();
		try {
			List<Booking> revs = bs.getAllBookings();
			revs.forEach(r -> r.printBookingsDetails());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void getBookingsBetweenDates(Scanner sc) {
		System.out.print("Enter start date (yyyy-MM-dd): ");
		String startDate = sc.next();

		System.out.print("Enter end date (yyyy-MM-dd): ");
		String endDate = sc.next();

		BookingService rs = new BookingServiceImpl();
		try {
			List<Booking> revs = rs.getBookingsBetweenDates(LocalDate.parse(startDate),
					LocalDate.parse(endDate));
			revs.forEach(r -> r.printBookingsDetails());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}


	public static void login(Scanner sc)
			throws SomethingWentWrongException,NoRecordFoundException {
		System.out.print(Color.CYAN+"Enter admin username: "+Color.RESET);
		String username = sc.next();

		System.out.print(Color.CYAN+"Enter admin password: "+Color.RESET);

		String password = sc.next();

		if (username.equals(Admin.USERNAME.getValue()) && password.equals(Admin.PASSWORD.getValue())) {
			System.out.println(Color.FOREST_GREEN_BACKGROUND+"Admin login successfull !"+Color.RESET);
			
			System.out.println();
			System.out.println(Color.FOREST_GREEN_BACKGROUND+"Welcome Admin !"+Color.RESET);
			
			System.out.println("-----------------------------------------------------------------------------");
		} else {
			System.out.println("Invalid username or password.");
		}
	}

}
