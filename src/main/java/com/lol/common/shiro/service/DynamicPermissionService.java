package com.lol.common.shiro.service;

import java.util.Map;

/**
 * 动态权限配置服务
 * 
 * @author cunxing
 *
 */
public interface DynamicPermissionService {

	/**
	 * 初始化权限配置；
	 * 加载默认配置和数据库中的配置
	 */
	void init();

	/**
	 * 更新权限配置
	 *
	 * @param newDefinitions 权限配置信息
	 */
	void updatePermission(Map<String, String> newDefinitions);

	/**
	 * 重新加载权限配置
	 */
	void reloadPermission();

}
