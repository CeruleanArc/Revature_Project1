package com.revature.beyondcon.ui;

import com.revature.beyondcon.models.Users;
import com.revature.beyondcon.services.ContactInfoService;

import java.util.Scanner;

public class MainMenu implements IMenu {
	
	private final Users user;
	private final ContactInfoService contactInfoService;
	
	public MainMenu(Users user, ContactInfoService contactInfoService) {
		this.user = user;
		this.contactInfoService = contactInfoService;
	}
	
	@Override
	public void start() {
		char input;
		boolean exit = false;
		Scanner scan = new Scanner(System.in);
		int userId = user.getId();
		boolean uType = user.getUType();
		int userContactId = contactInfoService.getContactInfoDAO().findByUserId(userId).getUserId();
		
		String userName;
		if (userContactId != 0) {
			userName = contactInfoService.getContactInfoDAO().findByUserId(userId).getFirstName();
		} else {
			userName = user.getUsername();
		}
		
		while (!exit) {
			System.out.println("\nWELCOME TO BEYONDCON 2023, " + userName.toUpperCase() + "!");
			System.out.println("\n[1] Record your contact information with BeyondCon 2023");
			System.out.println("[2] Learn about all BeyondCon 2023 Conventions");
			System.out.println("[3] Learn about all BeyondCon 2023 Speakers");
			System.out.println("[4] Learn about all BeyondCon 2023 Events");
			System.out.println("[5] Learn about all BeyondCon 2023 Vendors");
			System.out.println("[6] Attend BeyondCon 2023!");
			if (uType) System.out.println("[7] Go to the BeyondCon 2023 Administration Menu");
			System.out.println("[x] Logout");
			
			System.out.print("\nEnter: ");
			input = scan.next().charAt(0);
			
			switch (input) {
				case '1' -> {
					if (userContactId != 0) {
						System.out.println("\nWe already have your contact information! Would you like to change your contact information at this time? (Choose \"y\" or \"n\".");
						System.out.print("\nEnter: ");
						input = scan.next().charAt(0);
						
						switch (input) {
							case 'y' -> {
								editContactInfo();
								break;
							} case 'n' -> {
								break;
							} default -> {
								System.out.println("\nInvalid input!");
								break;
							}
						}
					} else {
						saveContactInfo();
					}
					break;
				} case '2' -> {
					break;
				} case '3' -> {
					break;
				} case '4' -> {
					break;
				} case '5' -> {
					break;
				} case '6' -> {
					break;
				} case '7' -> {
					break;
				}case 'x' -> {
					exit = true;
					System.out.println("\nYou have successfully logged out of BeyondCon 2023!");
					break;
				} default -> {
					break;
				}
			}
		}
	}
	
	public void saveContactInfo() {
		//Here is where I stopped for the night.
	}
	
	public void editContactInfo() {
	
	}
	
}
