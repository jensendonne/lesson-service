/**
 * 
 */
package app.lesson.lesnsrvc.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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
	
	/**
	 * yyyyMMdd格式字符串转日期
	 * @param yyyyMMdd
	 * @return
	 * @throws ParseException
	 */
	public static Date yyyyMMdd2Date(String yyyyMMdd) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.parse(yyyyMMdd);
	}
	
	/**
	 * 将日期对象转换为yyyyMMdd格式的字符串
	 * @param date
	 * @return
	 */
	public static String date2yyyyMMdd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	/**
	 * yyyy-MM-dd格式字符串转日期
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date str2Date(String dateStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(dateStr);
	}
	
	/**
	 * 将日期对象转换为yyyy-MM-dd格式的字符串
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(digestMD5("1234567890"));
	}
}
