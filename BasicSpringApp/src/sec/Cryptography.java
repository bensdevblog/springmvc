package sec;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;

/*
 * Cryptography
 * Handles password encryption, salt generation, and authentication
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
public class Cryptography {
	
	/*
	 * Authenticates a password attempt
	 * 
	 * @param attemptedPassword	Plain-text password attempt
	 * @param encrypedPassword	Encrypted password to compare to
	 * @param salt				Unique salt
	 * @return	true if authenticated, false if otherwise
	 */
	public boolean authenticate(String attemptedPassword,
			byte[] encryptedPassword, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		byte[] encryptedAttemptedPassword = getEncryptedPassword(
				attemptedPassword, salt);
		return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
	}
	/*
	 * Encrypt and salt a plain-text password (PBKDF2)(SHA1)
	 * 
	 * @param password	Plain-text password
	 * @param salt		Unique salt
	 * @return			Encrypted password w/ salt
	 */
	public byte[] getEncryptedPassword(String password, byte[] salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 160;
		int iterations = 20000;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations,
				derivedKeyLength);
		SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
		return f.generateSecret(spec).getEncoded();
	}
	/*
	 * Generates a random salt (SHA1)
	 * 
	 * @return a random salt
	 */
	public byte[] generateSalt() throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[8];
		random.nextBytes(salt);

		return salt;
	}
	/*
	 * Converts a byte array to hexadecimal string
	 * 
	 * @param array	Byte array to convert
	 * @return	Hex string from byte array
	 */
	public String toHexString(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}
	/*
	 * Converts a hexadecimal string to byte array
	 * 
	 * @param s	Hexadecimal string to convert
	 * @return	Byte array from hexadecimal string
	 */
	public byte[] toByteArray(String s) {
		return DatatypeConverter.parseHexBinary(s);
	}
}
