package com.kcrypt.service;

import java.lang.InternalError;
import com.kcrypt.db.ArrayListAuthStorage;
import com.kcrypt.db.IAuthStorage;
import com.kcrypt.models.User;

public class AuthService {

	IAuthStorage authStorage = new ArrayListAuthStorage();
	private Encryption encryption = new Encryption();

	public String getHashedPassword(String email) {
		User user = authStorage.getUser(email);

		if (user == null) {
			return "";
		}

		return user.getPassword();

	}

	public void createUser(String email, String password) {
		try {
			// hash password
			String hashedPassword = encryption.hash(password);
			// create new user

			System.out.printf("\n\n Hash : %s \n\n", hashedPassword);

			User user = new User(email, hashedPassword);

			// persist user in
			authStorage.createUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String loginUser(String email, String password) {
		// TODO: get user with matching email

		// TODO: compare password

		// TODO: return user

		return "";
	}
}
