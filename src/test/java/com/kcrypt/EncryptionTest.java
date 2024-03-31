package com.kcrypt;

import org.junit.jupiter.api.Test;

import com.kcrypt.service.Encryption;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionTest {

	public static Encryption encryption = new Encryption();

	@Test
	void testHashMatch() {
		try {
			String password = "1234";
			String hash = encryption.hash(password);

			boolean isValid = encryption.comparePasswords(password, hash);

			assertTrue(isValid);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	void testHashInvalidPassword() {
		try {
			String password = "1234";
			String invalidPassword = "12345";
			String hash = encryption.hash(password);

			boolean isValid = encryption.comparePasswords(invalidPassword, hash);

			assertFalse(isValid);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testEncryption() {
		String plainText = "This is a testing phrase";

		String password = "12345";

		try {
			String hash = encryption.hash(password);
			String hiddenText = encryption.encrypt(hash, plainText);

			assertEquals(plainText, encryption.decrypt(hash, hiddenText));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	void testEncryptionInvalid() {
		String plainText = "This is a testing phrase";
		String plainTextInvalid = "This is a testing phrase changed";

		String password = "12345";

		try {
			String hash = encryption.hash(password);
			String hiddenText = encryption.encrypt(hash, plainText);

			assertNotEquals(plainTextInvalid, encryption.decrypt(hash, hiddenText));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
