package com.kcrypt.db;

import com.kcrypt.models.User;

public interface IAuthStorage {
	// create new user
	void createUser(User user);

	// get registered user
	User getUser(String email);
}
