package com.revature.beyondcon;

import com.revature.beyondcon.daos.UsersDAO;
import com.revature.beyondcon.services.UsersService;
import com.revature.beyondcon.ui.LoginMenu;

public class Main {
	
	public static void main(String[] args) {
		
		new LoginMenu(new UsersService(new UsersDAO())).start();
		
	}

}