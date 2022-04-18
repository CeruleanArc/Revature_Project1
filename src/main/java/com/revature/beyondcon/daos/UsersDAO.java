package com.revature.beyondcon.daos;

import com.revature.beyondcon.connection.DatabaseConnection;
import com.revature.beyondcon.models.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO implements CrudDAO<Users> {
	
	Connection con = DatabaseConnection.getCon();
	
	@Override
	public int save(Users obj) {
		int n = 0;
		
		try {
			PreparedStatement ps = con.prepareStatement("INSERT INTO users (username, password, u_type) VALUES (?, ?, ?)");
			ps.setString(1, obj.getUsername());
			ps.setString(2, obj.getPassword());
			ps.setBoolean(3, obj.getUType());
			
			n = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return n;
	}
	
	@Override
	public List<Users> findAll() {
		List<Users> userList = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Users user = new Users();
				
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUType(rs.getBoolean("u_type"));
				
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userList;
	}
	
	@Override
	public Users findById(int id) {
		return null;
	}
	
	@Override
	public boolean update(Users updatedObj) {
		return false;
	}
	
	@Override
	public boolean removeById(int id) {
		return false;
	}
	
	public List<String> findAllUsernames() {
		List<String> usernameList = new ArrayList<>();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT (username) FROM users");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				usernameList.add(rs.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return usernameList;
	}
	
	public Users getByUsername (String username) {
		Users user = new Users();
		
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE username = ?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setUType(rs.getBoolean("u_type"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
}
