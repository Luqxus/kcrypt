package com.kcrypt.service;

import java.util.Map;
import java.util.Set;

public interface IPasswordService {
	// save password
	public void save(String passwordHash, String site, String password);

	// get password
	public Set<Map<String, Map<String, String>>> getPasswords();

	// receive encrypted password & return plainPassword
	public String show(String passwordHash, String encryptedPassword);
}
