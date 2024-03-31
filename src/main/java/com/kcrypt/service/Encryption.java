package com.kcrypt.service;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Encryption {
	public static String hashPassword(String plainPassword) throws NoSuchAlgorithmException {
		// hash password

		// hashing algorithm
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		// generate random salt bytes
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[16];
		rand.nextBytes(salt);

		// add salt to digest
		digest.update(salt);

		// hash text
		byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));

		// convert hash bytes to hex string and return
		return toHexString(hash);
	}

	public static String toHexString(byte[] bytes) {
		// convert byte[] to hex String
		StringBuilder hexString = new StringBuilder();
		for (byte b : bytes) {
			String hex = Integer.toHexString(0xff & b);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}

		return hexString.toString();
	}
}
