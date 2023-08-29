package com.masai.ui;

import java.util.Scanner;

import com.masai.color.Color;
import com.masai.exceptions.NoHomeRecordFoundException;
import com.masai.exceptions.NoRecordFoundException;
import com.masai.exceptions.SomethingWentWrongException;

public class MainUI {
	public static void main(String[] args)
			throws SomethingWentWrongException, NoRecordFoundException, NoHomeRecordFoundException {
		Scanner sc = new Scanner(System.in);

		int choice = 0;
		do {
			System.out.println("╔═══════════════ Main Menu ═══════════════╗");
			System.out.println("║ 1. Admin Login                          ║");
			System.out.println("║ 2. Customer Login                       ║");
			System.out.println("║ 3. Customer Registration                ║");
			System.out.println("║ 0. Exit                                 ║");
			System.out.println("╚═════════════════════════════════════════╝");
			System.out.print(Color.CYAN+"Enter Selection: "+Color.RESET);

			choice = sc.nextInt();

			switch (choice) {
			case 1:
				AdminUI.login(sc);
				AdminUI.adminMenu(sc);
				break;
			case 2:
				CustomerUI.login(sc);
				break;
			case 3:
				CustomerUI.customerRegister(sc);
				break;
			case 0:
				System.out.println(Color.FOREST_GREEN_BACKGROUND+"Thanks for using the services."+Color.RESET);
				break;
			default:
				System.out.println(Color.FOREST_GREEN_BACKGROUND+"Invalid Selection, try again."+Color.RESET);
			}
		} while (choice != 0);
		sc.close();
	}

}
