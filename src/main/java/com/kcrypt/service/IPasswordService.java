package com.kcrypt.service;

import java.util.Map;
import java.util.Set;

public interface IPasswordService {
	// save password
	public void save(String site, String password);

	// get password
	public Set<Map<String, String>> getPasswords();

	// receive encrypted password & return plainPassword
	public void show(String encryptedPassword);
}
