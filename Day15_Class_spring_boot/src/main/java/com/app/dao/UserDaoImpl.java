package com.app.dao;

import java.util.List;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.pojos.Role;
import com.app.pojos.User;

@Repository // to tell Sc class conatin data access logic
public class UserDaoImpl implements IUserDao {
//Dependency : Javax.persistence.Entity Manager (super i/f of org.hibernate.Session)
	@Autowired
	private EntityManager manager;

	@Override
	public User ValidateUser(String email, String password) {
		String jpql = "select u from User u where u.email=:em and u.password=:pass";
		return manager.createQuery(jpql, User.class).setParameter("em", email).setParameter("pass", password)
				.getSingleResult();
	}

	@Override
	public List<User> getAllVendors() {
		String jpql = "select u from User u where u.userRole=:role";

		return manager.createQuery(jpql, User.class).setParameter("role", Role.VENDOR).getResultList();
	}

	@Override
	public String deleteVendorDetails(User vendor) {
		String mesg = "Vendor details removed with VID " + vendor.getId();
		manager.remove(vendor);
		return mesg;
	}

	@Override
	public User findByUserId(int userId) {

		return manager.find(User.class, userId);
	}

	@Override
	public String registerVendor(User vendor) {
		manager.persist(vendor);
		return "Vendor Registered Succcessfully With ID = "+vendor.getId();
	}

}
