package com.lol.common.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.lol.common.service.UserLoginService;
import com.lol.common.util.SpringUtils;

import java.util.concurrent.atomic.AtomicInteger;

public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

	private Cache<String, AtomicInteger> passwordRetryCache;

	public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//		String username = (String) token.getPrincipal();
//		AtomicInteger retryCount = passwordRetryCache.get(username);
//		if (retryCount == null) {
//			retryCount = new AtomicInteger();
//			passwordRetryCache.put(username, retryCount);
//		}
		//System.out.println("----------retryCount:" + retryCount.get());
		// 重试密码超过3次抛出异常，retryCount + 1
		/*if (retryCount.incrementAndGet() > 3) {
			UserLoginService userLoginService = SpringUtils.getBean("userLoginService");
			userLoginService.lock(username, 3600);//锁定一个小时
			passwordRetryCache.remove(username);
			throw new ExcessiveAttemptsException();
		}*/

		boolean matches = super.doCredentialsMatch(token, info);
//		if (matches) {
//			passwordRetryCache.remove(username);
//		}else{
//			passwordRetryCache.put(username, retryCount);
//		}
		return matches;
	}
}
