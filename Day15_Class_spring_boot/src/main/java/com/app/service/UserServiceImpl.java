package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUserDao;
import com.app.pojos.Role;
import com.app.pojos.User;

@Service // to tell class Hold B.L
@Transactional
public class UserServiceImpl implements IUserService {
//dependency:dao i/f
	@Autowired
	private IUserDao userDao;

	@Override
	public User validateUser(String email, String password) {
		System.out.println("in service " + email + " " + password);
		// invoke Dao's methods
		return userDao.ValidateUser(email, password);
	}

	@Override
	public List<User> getAllVendors() {

		return userDao.getAllVendors();
	}

	@Override
	public String deleteVendorDetails(int vendorId) {
//get vendor details from Dao layer
		User vendor = userDao.findByUserId(vendorId);
		if (vendor != null)
			return userDao.deleteVendorDetails(vendor);
		return "Vendor Deletion Failed.!!";
	}

	@Override
	public String registerVendor(User vendor) {
		//vendor: transient
		vendor.setUserRole(Role.VENDOR);
		return userDao.registerVendor(vendor);
	}

}
