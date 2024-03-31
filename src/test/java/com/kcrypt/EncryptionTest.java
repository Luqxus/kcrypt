package com.kcrypt;

import org.junit.jupiter.api.Test;

import com.kcrypt.service.Encryption;

import static org.junit.jupiter.api.Assertions.*;

public class EncryptionTest {

	@Test
	void testHashMatch() {
		try {
			String password = "1234";
			String hash = Encryption.hash(password);

			boolean isValid = Encryption.comparePasswords(password, hash);

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
			String hash = Encryption.hash(password);

			boolean isValid = Encryption.comparePasswords(invalidPassword, hash);

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
			String hash = Encryption.hash(password);
			String hiddenText = Encryption.encrypt(hash, plainText);

			assertEquals(plainText, Encryption.decrypt(hash, hiddenText));

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
			String hash = Encryption.hash(password);
			String hiddenText = Encryption.encrypt(hash, plainText);

			assertNotEquals(plainTextInvalid, Encryption.decrypt(hash, hiddenText));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
