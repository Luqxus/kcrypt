package com.kcrypt.db;

import java.util.ArrayList;

import com.kcrypt.models.User;

public class ArrayListAuthStorage implements IAuthStorage {
	ArrayList<User> users = new ArrayList<>();

	@Override
	public void createUser(User user) {
		users.add(user);

		System.out.println(user.getEmail());
		System.out.println(user.getPassword());

	}

	@Override
	public User getUser(String email) {
		System.out.println("--------------------------");
		System.out.println(email);
		System.out.println(users);

		System.out.println("--------------------------");

		for (User user : users) {
			if (user.getEmail() == email) {
				return user;
			}
		}
		return null;
	}
}
