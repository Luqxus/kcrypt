package com.kcrypt.db;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HashMapPasswordStorage implements IPasswordStorage {
	Set<Map<String, Map<String, String>>> storage = new HashSet<>();

	@Override
	public void save(String site, String encryptedPwdMap) {
		int pwdID = (storage.size() + 1);

		// persist password into map
		Map<String, Map<String, String>> data = new HashMap<>() {
			{
				put(Integer.toString(pwdID), new HashMap<>() {
					{
						put(site, encryptedPwdMap);
					}
				});
			}
		};

		storage.add(data);
	}

	@Override
	public Set<Map<String, Map<String, String>>> getPasswords() {
		return storage;
	}

}
