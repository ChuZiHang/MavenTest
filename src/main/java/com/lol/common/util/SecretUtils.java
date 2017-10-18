package com.lol.common.util;

import java.io.IOException;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加密工具类
 * 
 * @author cunxing
 *
 */
public class SecretUtils {

	public static final String MD5 = "md5";

	/**
	 * 随机产生salt
	 * 
	 * @return
	 */
	public static String randomSalt() {
		return new SecureRandomNumberGenerator().nextBytes().toHex();
	}

	/**
	 * 对密码加密 算法规则两次MD5\salt=username+salt2
	 * 
	 * @param username
	 * @param password
	 * @param salt2
	 *            数据库中的salt
	 * @return
	 */
	public static String encryptPass(String username, String password, String salt2) {
		SimpleHash hash = new SimpleHash(MD5, password, username + salt2, 2);
		return hash.toHex();
	}

	/**
	 * 1-base64编码<br>
	 * 2-倒序<br>
	 * 3-奇偶交换<br>
	 * 
	 * @param str
	 * @return
	 */
	public static String encode(String str) {
		BASE64Encoder base64Encoder = new BASE64Encoder();
		String vender = base64Encoder.encode(str.getBytes());
		char[] temp = vender.toCharArray();
		char pos;
		for (int i = 0, len = temp.length; i < len; i++) {
			pos = temp[i];
			temp[i] = temp[len - 1 - i];
			temp[len - 1 - i] = pos;
		}
		for (int i = 0, len = temp.length; i < len; i += 2) {
			if (i != (len - 1)) {
				pos = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = pos;
			}
		}
		return String.valueOf(temp);
	}

	/**
	 * 1-奇偶对调<br>
	 * 2-逆序<br>
	 * 3-base64
	 * 
	 * @param str
	 * @return
	 */
	public static String decode(String str) {
		char[] temp = str.toCharArray();
		char pos;
		for (int i = 0, len = temp.length; i < len; i += 2) {
			if (i != (len - 1)) {
				pos = temp[i];
				temp[i] = temp[i + 1];
				temp[i + 1] = pos;
			}
		}
		for (int i = 0, len = temp.length; i < len; i++) {
			pos = temp[i];
			temp[i] = temp[len - 1 - i];
			temp[len - 1 - i] = pos;
		}
		BASE64Decoder base64Decoder = new BASE64Decoder();
		byte[] vender = null;
		try {
			vender = base64Decoder.decodeBuffer(String.valueOf(temp));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String(vender);
	}
	
	
	public static void main(String[] args) {
	    String s =encryptPass("mayun", "mayun", "72b55c51f8dd23fe2ff4bc6a77d14b12");
	    System.out.println(s);
    }

}
