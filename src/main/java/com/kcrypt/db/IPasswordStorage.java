package com.kcrypt.db;

import java.util.Map;
import java.util.Set;

public interface IPasswordStorage {

	// save password to the storage
	public void save(String site, String encryptedPwdMap);

	// get password from storage
	public Set<Map<String, Map<String, String>>> getPasswords();
}
