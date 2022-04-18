package com.revature.beyondcon.services;

import com.revature.beyondcon.daos.ContactInfoDAO;
import com.revature.beyondcon.daos.UsersDAO;

public class ContactInfoService {
	
	private final ContactInfoDAO contactInfoDAO;
	private final UsersDAO usersDAO;
	
	public ContactInfoService(ContactInfoDAO contactInfoDAO, UsersDAO usersDAO) {
		this.contactInfoDAO = contactInfoDAO;
		this.usersDAO = usersDAO;
	}
	
	public ContactInfoDAO getContactInfoDAO() {
		return contactInfoDAO;
	}
	
	public UsersDAO getUsersDAO() {
		return usersDAO;
	}
	
}
