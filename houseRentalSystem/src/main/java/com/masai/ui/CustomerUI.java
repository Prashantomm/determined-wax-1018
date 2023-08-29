package com.masai.ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.masai.color.Color;
import com.masai.entity.Booking;
import com.masai.entity.Customer;
import com.masai.entity.Home;
import com.masai.entity.Payment;
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

public class CustomerUI {
	static void displayCustomerMenu() {
		System.out.println("╔═════════════ Customer Menu ══════════════╗");
		System.out.println("║ 1. Search and Filter Homes               ║");
		System.out.println("║ 2. Make Bookings                         ║");
		System.out.println("║ 3. Cancel Booking                        ║");
		System.out.println("║ 4. view Bookings                         ║");
		System.out.println("║ 5. Check Home Availability               ║");
		System.out.println("║ 6. Make Payments                         ║");
		System.out.println("║ 7. view Payment History                  ║");
		System.out.println("║ 8. View Rental Policies                  ║");
		System.out.println("║ 0. Logout                                ║");
		System.out.println("╚═════════════════════════════════════════=╝");
	}
	
	static void customerMenu(Scanner sc)
			throws SomethingWentWrongException, NoRecordFoundException {
		int choice = 0;
		do {
			displayCustomerMenu();
			System.out.print("Enter selection: ");
			choice = sc.nextInt();
			switch (choice) {
			case 1:
				searchAndFilterHomes(sc);
				break;
			case 2:
				makeBooking(sc);
				break;
			case 3:
				cancelBooking(sc);
				break;
			case 4:
				viewBooking(sc);
				break;
			case 5:
				checkHomeAvailability(sc);
				break;
			
			case 6:
				addPaymentMethod(sc);
				break;
			case 7:
				viewPaymentHistory(sc);
				break;
			
			case 8:
				viewRentalPolicies();
				break;
			case 0:
				System.out.println("Logout Successfully");
				break;
			default:
				System.out.println("Invalid Selection, try again");
			}
		} while (choice != 0);
	}
	
	public static void generateReceipt(Payment payment) {
		System.out.println("----------------------------");
		System.out.println("Reservation Receipt:");
		Booking book = payment.getBooking();
		System.out.println("booking ID: " + book.getBookingId());
		System.out.println("Home Details:");
		System.out.println("Location: " + book.getHome().getArea());
		System.out.println("Type: " + book.getHome().getType());
		System.out.println("Start Date: " + book.getStartDate());
		System.out.println("End Date: " + book.getEndDate());
		System.out.println("Payment ID: " + payment.getPaymentId());
		System.out.println("Payment Date: " + payment.getPaymentDate());
		System.out.println("Amount Paid: " + payment.getAmount());
		System.out.println("Payment Method: " + payment.getPaymentMethod());
		System.out.println("----------------------------");
	}
	
	private static void addPaymentMethod(Scanner sc) {

		try {
			System.out.println("Enter Booking ID for Payment: ");
			Long bookingId = sc.nextLong();
			BookingService bs = new BookingServiceImpl();
			Booking book = bs.getBookingById(bookingId);
			if (book == null) {
				System.out.println("Booking not found with the given ID");
				return;
			}
			long durationInDays = ChronoUnit.DAYS.between(book.getStartDate(), book.getEndDate());
			double totalAmount = book.getHome().getPrice() * durationInDays;

			System.out.println("You have to pay : " + totalAmount + " Rupees.");
			System.out.println("Enter Payment Amount to be paid below: ");
			double amount = sc.nextDouble();

			if (amount < totalAmount) {
				System.out.println("Please Enter the right amount.");
				return;
			}

			sc.nextLine(); // Consume the newline character after reading the BigDecimal

			System.out.println("Enter Payment Method (e.g., Paytm, PhonePe, GPay, Credit Card, etc.): ");
			String paymentMethod = sc.nextLine();

			// Create a new Payment object with the provided data
			Payment payment = new Payment(book, LocalDate.now(), BigDecimal.valueOf(totalAmount), paymentMethod);
			PaymentService ps = new PaymentServiceImpl();
			Payment payment1 = ps.makePayment(payment);
			generateReceipt(payment1);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
//	private static void viewRentalCharges(Scanner sc) {
//		HomeService hs = new HomeServiceImpl();
//		try {
//			hs.getAllHomes();
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	
	private static void checkHomeAvailability(Scanner sc) {
		HomeService cs = new HomeServiceImpl();
		System.out.println("Enter Home ID to check Availabilty: ");
		Long homeId = sc.nextLong();
		try {
			Home home = cs.getHomeById(homeId);
			if (home != null && home.isAvailability()) {
				AdminUI.printHomeDetails(home);
			} else {
				System.out.println("Home is not available.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void viewRentalPolicies() {
		System.out
				.println("-------------------------------------------------------------------------------------------");
		System.out.println(Color.ORANGE+"Rental Policies:"+Color.RESET);
		System.out.println("1. Minimum rental duration: 2 days");
		System.out.println("2. Maximum rental duration: 30 days");
		System.out.println("3. Base price per day: 1500rs");
	
		System.out.println("4. Security deposit: 200rs");
		System.out.println("5. Late penalty: 25rs per day");
		System.out.println("6. Age requirement: Minimum age of the renter should be 21");
		
		System.out.println("7. Smoking policy: Strictly no smoking inside the home");
		System.out.println("8. Cleaning fee: 2500rs for excessively dirty home");
		System.out.println("9. Toll charges: Customer responsible for toll charges");
		System.out.println("10. home damage policy: Customer responsible for damages");
		System.out.println("11. Insurance options: Various insurance coverage options available");
		System.out.println("12. Early return policy: No refunds for early returns");
		System.out.println("13. Booking cancellation policy: Full refund if canceled 48 hours ");
		System.out
				.println("-------------------------------------------------------------------------------------------");
	}
	
	private static void searchAndFilterHomes(Scanner sc) throws SomethingWentWrongException {
		System.out.println("╔═════════════════════════════════╗");
		System.out.println("║  Search and Filter homes:       ║");
		System.out.println("╠═════════════════════════════════╣");
		System.out.println("║ 1. Search by Loacation          ║");
		System.out.println("║ 2. Search by Type               ║");
		System.out.println("║ 3. Search by Price Range        ║");
		System.out.println("║ 4. Show All Homes               ║");
		System.out.println("╚═════════════════════════════════╝");

		System.out.print(Color.CYAN+"Enter selection: "+Color.RESET);
		int option = sc.nextInt();

		switch (option) {
		case 1:
			searchHomesByArea(sc);
			break;
		case 2:
			searchHomesByType(sc);
			break;
		case 3:
			searchHomesByPrice(sc);
			break;
		case 4:
			AdminUI.getAllHomes();
			break;
		default:
			System.out.println("Invalid Selection, try again");
		}
	}
	private static void searchHomesByArea(Scanner sc) {
		System.out.print("Enter Location: ");
		String area = sc.next();
		HomeService cs = new HomeServiceImpl();
		try {
			List<Home> homes = cs.searchHomesByArea(area);
			homes.forEach(c -> AdminUI.printHomeDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void searchHomesByType(Scanner sc) {
		System.out.print("Enter Type(1BHK/2BHK/3BHK/4BHK: ");
		String type = sc.next();
		HomeService cs = new HomeServiceImpl();
		try {
			List<Home> homes = cs.searchHomesByType(type);
			homes.forEach(c -> AdminUI.printHomeDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void searchHomesByPrice(Scanner sc) {
		System.out.print("Enter Start Price: ");
		double start = sc.nextDouble();
		System.out.print("Enter End Price: ");
		double end = sc.nextDouble();
		HomeService cs = new HomeServiceImpl();
		try {
			List<Home> homes = cs.searchHomesByPrice(start, end);
			homes.forEach(c -> AdminUI.printHomeDetails(c));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void makeBooking(Scanner sc) throws  SomethingWentWrongException {

		try {
			System.out.print("Enter Home ID for Booking: ");
			Long homeId = sc.nextLong();
			System.out.println("Enter Customer ID for booking: ");
			Long customerId = sc.nextLong();

			HomeService cs = new HomeServiceImpl();
			CustomerService ct = new CustomerServiceImpl();
			Home home = cs.getHomeById(homeId);
			Customer cust = ct.getCustomerById(customerId);

			if (home == null) {
				throw new  SomethingWentWrongException("Home Not Found.");
			} else if (cust == null) {
				throw new NoRecordFoundException("Customer ID is Invalid.");
			} else if (!home.isAvailability()) {
				throw new  SomethingWentWrongException(Color.GREEN_BACKGROUND+"Congratulation!!! Your Home is booked."+Color.RESET);
			}

			System.out.print("Enter Start Date (YYYY-MM-DD): ");
			String startDateStr = sc.next();
			LocalDate startDate = LocalDate.parse(startDateStr);

			System.out.print("Enter End Date (YYYY-MM-DD): ");
			String endDateStr = sc.next();
			LocalDate endDate = LocalDate.parse(endDateStr);
			Booking b = new Booking(home, cust, startDate, endDate, false);
			BookingService rs = new BookingServiceImpl();
			Booking res = rs.makeBooking(b);
			res.printBookingsDetails();
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	private static void cancelBooking(Scanner sc) throws NoRecordFoundException {
		System.out.println("Enter Booking ID for cancellation: ");
		Long bookingId = sc.nextLong();
		BookingService rs = new BookingServiceImpl();
		try {
			rs.cancelBooking(bookingId);
			System.out.println(Color.GREEN_BACKGROUND+"Booking Cancelled."+Color.RESET);
		} catch (NoRecordFoundException | SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}

	
	private static void viewBooking(Scanner sc) {
		System.out.print("Enter Customer ID: ");
		Long customerId = sc.nextLong();
		BookingService rs = new BookingServiceImpl();
		try {
			List<Booking> revs = rs.getBookingsByCustomer(customerId);
			revs.forEach(r -> r.printBookingsDetails());
		} catch (NoRecordFoundException e) {
			System.out.println(e.getMessage());
		}

	}

	
	
	private static void viewPaymentHistory(Scanner sc) {
		System.out.print("Enter Customer ID: ");
		Long customerId = sc.nextLong();
		PaymentService ps = new PaymentServiceImpl();

		try {
			List<Payment> payments = ps.getPaymentsByBooking(customerId);
			payments.forEach(p -> generateReceipt(p));
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}


	public static void login(Scanner sc) {
		System.out.println("Enter Username : ");
		String username = sc.next();
		System.out.println("Enter Password : ");
		String password = sc.next();
		CustomerService cs = new CustomerServiceImpl();
		try {
			cs.login(username, password);
			try {
				CustomerUI.customerMenu(sc);
			} catch (  NoRecordFoundException e) {
				e.printStackTrace();
			}
		} catch (SomethingWentWrongException e) {
			System.out.println(e.getMessage());
		}
	}

	
	public static void customerRegister(Scanner sc) {
		AdminUI.addCustomer(sc);
	}



}
