package com.lol.common.shiro;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

public class SaltTest {

	public static void main(String[] args) {
		String algorithmName = "md5";
		String username = "admin";
		String password = "123456";
		String salt = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		salt = "54cc0acd4a82868e8c5d81e1c54eae3f";

		SimpleHash hash = new SimpleHash(algorithmName, password, username + salt, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println("pass:" + encodedPassword);
		System.out.println("salt:" + salt);
	}

}
