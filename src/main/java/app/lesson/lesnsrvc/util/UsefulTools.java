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
public class UsefulTools {

	public static String digestMD5(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data.getBytes("UTF-8"));
		byte[] digest = md.digest();
		return new BigInteger(1, digest).toString(16);
	}
	
	/**
	 * 生成指定长度的随机数字
	 * @param length 随机数长度
	 * @return
	 */
	public static String generateRandomNumber(int length) {
		String result = "";
		if (length > 0) {
			int temp;
			for (int i = 0; i < length; i++) {
				temp = (int) (Math.random() * 10);
				result = result + temp;
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(generateRandomNumber(4));
	}
}
