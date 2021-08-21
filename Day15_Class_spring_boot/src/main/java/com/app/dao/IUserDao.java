package com.app.dao;

import java.util.List;

import com.app.pojos.User;

public interface IUserDao {
//add a method For user Login
	User ValidateUser(String email,String password);
	//add a method for listing all vendor
	List<User> getAllVendors();
	//delete vendor details
	String deleteVendorDetails(User vendor);
	//get vendor details from it's PK
	User findByUserId(int userId);
	//register new vendor
	String registerVendor(User vendor); //transient pojo
}
