/**
 * 
 */
package app.lesson.lesnsrvc.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Rocketman
 *
 */
public class SecurityUtils {

	public static String digestSHA256(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(data.getBytes("UTF-8"));
		byte[] digest = md.digest();
		return new BigInteger(1, digest).toString(16);
	}
}
