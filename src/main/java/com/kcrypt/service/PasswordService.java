package com.kcrypt.service;

import java.util.Map;
import java.util.Set;

import com.kcrypt.db.HashMapPasswordStorage;
import com.kcrypt.db.IPasswordStorage;

public class PasswordService implements IPasswordService {
	private AuthService authService = new AuthService();
	private IPasswordStorage passwordStorage = new HashMapPasswordStorage();
	private Encryption encryption = new Encryption();

	// using email as a verification string for now
	// will probably opt for JWT later
	@Override
	public void save(String passwordHash, String site, String password) {
		// get password hash
		try {

			// encrypt password
			String hiddedPassword = encryption.encrypt(passwordHash, password);

			// persist map to storage
			passwordStorage.save(site, hiddedPassword);
		} catch (Exception e) {
			e.printStackTrace();

			// you can do better than this
			System.exit(0);
		}

	}

	@Override
	public Set<Map<String, Map<String, String>>> getPasswords() {
		return passwordStorage.getPasswords();
	}

	@Override
	public String show(String passwordHash, String encryptedPassword) {
		try {

			// decrypt
			String plainPassword = encryption.decrypt(passwordHash, encryptedPassword);

			// return plain password
			return plainPassword;
		} catch (Exception e) {
			e.printStackTrace();
			return encryptedPassword;
		}
	}

}
