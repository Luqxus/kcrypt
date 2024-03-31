package com.kcrypt.service;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	private static byte[] salt;

	public Encryption() {
		// generate random salt bytes
		SecureRandom rand = new SecureRandom();
		salt = new byte[16];
		rand.nextBytes(salt);
	}

	public String hash(String plainPassword) throws NoSuchAlgorithmException {
		// hash password

		// hashing algorithm
		MessageDigest digest = MessageDigest.getInstance("SHA-256");

		// if (salt == null || salt.length == 0) {
		// // generate random salt bytes
		// SecureRandom rand = new SecureRandom();
		// salt = new byte[16];
		// rand.nextBytes(salt);
		// }

		// add salt to digest
		digest.update(salt);

		// hash text
		byte[] hash = digest.digest(plainPassword.getBytes(StandardCharsets.UTF_8));

		// convert hash bytes to hex string and return
		return toHexString(hash);
	}

	public boolean comparePasswords(String plaintextPassword, String hashedPassword) {
		try {
			// Hash the plaintext password
			String hashedInputPassword = hash(plaintextPassword);

			// Compare the hashed plaintext password with the stored hashed password
			return hashedInputPassword.equals(hashedPassword);
		} catch (NoSuchAlgorithmException e) {
			// Handle hashing algorithm not found exception
			e.printStackTrace();
			return false;
		}
	}

	public String encrypt(String hash, String plainText)
			throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException,
			NoSuchAlgorithmException, InvalidKeySpecException {
		System.out.printf("\n\n\n\n %s \n\n %s \n\n\n\n", salt.toString(), hash);

		SecretKey secretKey = generaSecretKey(hash);

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);

		byte[] b = cipher.doFinal(plainText.getBytes());

		return Base64.getEncoder().encodeToString(b);
	}

	public String decrypt(String hash, String encryptedText)
			throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException,
			InvalidKeySpecException, InvalidKeyException {
		SecretKey secretKey = generaSecretKey(hash);
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] b = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
		return new String(b);
	}

	public String toHexString(byte[] bytes) {
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

	private SecretKey generaSecretKey(String hash) throws NoSuchAlgorithmException, InvalidKeySpecException {

		// encryption algorithm
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		KeySpec spec = new PBEKeySpec(hash.toCharArray(), hash.getBytes(), 10000, 128);

		SecretKey key = factory.generateSecret(spec);

		return new SecretKeySpec(key.getEncoded(), "AES");

	}
}
