package com.revature.beyondcon.ui;

import com.revature.beyondcon.daos.ContactInfoDAO;
import com.revature.beyondcon.daos.UsersDAO;
import com.revature.beyondcon.models.Users;
import com.revature.beyondcon.services.ContactInfoService;
import com.revature.beyondcon.services.UsersService;

import java.util.Scanner;

public class LoginMenu implements IMenu {
	private final UsersService usersService;
	
	public LoginMenu(UsersService usersService) {
		this.usersService = usersService;
	}
	
	Scanner scan = new Scanner(System.in);
	Users user = new Users();
	
	@Override
	public void start() {
		char input;
		boolean exit = false;
		
		while (!exit) {
			System.out.println("\nWELCOME TO BEYONDCON2023 LOGIN AND REGISTRATION");
			System.out.println("\n[1] Log in");
			System.out.println("[2] Create new account");
			System.out.println("[x] Exit");
			
			System.out.print("\nEnter: ");
			input = scan.next().charAt(0);
			
			switch (input) {
				case '1' -> {
					login();
					break;
				} case '2' -> {
					createAccount();
					break;
				} case 'x' -> {
					System.out.println("\nThank you for visiting us. Follow us on Twitter at @beyondcon23 for more updates! Have a great day!");
					exit = true;
					break;
				} default -> {
					System.out.println("\nInvalid input!");
					break;
				}
			}
		}
	}
	
	private void createAccount() {
		String username = null;
		String password1 = null;
		String password2;
		boolean uType = false;
		boolean createNewUser = false;
		boolean createUsername = false;
		boolean createPassword = false;
		
		System.out.println("\nCREATING A NEW ACCOUNT...");
		
		while (!createNewUser) {
			while (!createUsername) {
				System.out.print("\nEnter your new username: ");
				username = scan.next().toLowerCase();
				
				if (!usersService.isDupUsername(username)) {
					if (usersService.isValidUsername(username)) {
						user.setUsername(username);
						createUsername = true;
					} else {
						System.out.println("\nInvalid username! Your username must be between 6 and 20 characters long.");
					}
				}
			}
			
			while (!createPassword) {
				System.out.print("\nEnter your new password: ");
				password1 = scan.next();
				
				System.out.print("\nEnter the same password a second time to confirm it: ");
				password2 = scan.next();
				
				if (password1.equals(password2)) {
					if (usersService.isValidPassword(password1)) {
						user.setPassword(password1);
						createPassword = true;
					} else {
						System.out.println("\nInvalid password! Your password must be at least 8 characters long, and it must include at least one uppercase letter, one lowercase letter, one number, and one special character.");
					}
				} else {
					System.out.println("\nYour password did not match! Please try again.");
				}
			}
			
			user.setUType(uType);
			boolean confirm = false;
			
			while(!confirm) {
				System.out.println("\nPlease confirm your new account credentials (enter \"y\" or \"n\").");
				System.out.println("\nUsername: " + username);
				System.out.println("Password: " + password1);
				System.out.print("\nEnter: ");
				char input = scan.next().charAt(0);
				
				switch (input) {
					case 'y' -> {
						usersService.getUsersDAO().save(user);
						System.out.println("\nYour new account was created successfully!");
						confirm = true;
						break;
					} case 'n' -> {
						confirm = true;
						break;
					} default -> {
						System.out.println("Invalid input!");
						break;
					}
				}
			}
			
			createNewUser = true;
		}
	}
	
	private void login() {
		boolean loggingIn = false;
		
		while (!loggingIn) {
			System.out.print("\nUsername: ");
			user.setUsername(scan.next().toLowerCase());
			
			System.out.print("\nPassword: ");
			user.setPassword(scan.next());
			
			if (usersService.isValidLogin(user)) {
				user = usersService.getUsersDAO().getByUsername(user.getUsername());
				new MainMenu(user, new ContactInfoService(new ContactInfoDAO(), new UsersDAO())).start();
			} else {
				System.out.println("\nInvalid login information!");
			}
			
			loggingIn = true;
		}
	}

}
