package com.revature.beyondcon.services;

import com.revature.beyondcon.daos.UsersDAO;
import com.revature.beyondcon.models.Users;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class UsersService {
	
	private final UsersDAO usersDAO;
	
	public UsersService(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
	
	public UsersDAO getUsersDAO() {
		return usersDAO;
	}
	
	public boolean isDupUsername(String username) {
		List<String> usernameList = usersDAO.findAllUsernames();
		
		for (String u : usernameList) {
			if (u.equals(username)) {
				return true;
			}
		}
		return false;
		
	}
	
	public boolean isValidUsername(@NotNull String username) {
		return username.matches("^(?=[a-zA-Z0-9._]{6,20}$)(?!.*[_.]{2})[^_.].*[^_.]$");
	}
	
	public boolean isValidPassword(@NotNull String password) {
		return password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$");
	}
	
	public boolean isValidLogin(Users user) {
		List<Users> userList = usersDAO.findAll();
		
		for (Users u : userList) {
			if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isAdmin(boolean uType) {
		return uType;
	}
	
}
