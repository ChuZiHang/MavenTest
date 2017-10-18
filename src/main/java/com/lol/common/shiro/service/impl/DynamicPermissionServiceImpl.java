package com.lol.common.shiro.service.impl;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.lol.common.Constants;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds0.SysPermissionExample;
import com.lol.common.service.SysPermissionService;
import com.lol.common.shiro.service.DynamicPermissionService;

/**
 * 动态加载权限配置信息实现类
 * 
 * @author cunxing
 *
 */
@Component
public class DynamicPermissionServiceImpl implements DynamicPermissionService {

	private static final Logger logger = LoggerFactory.getLogger(DynamicPermissionServiceImpl.class);

	@Resource
	private AbstractShiroFilter shiroFilter;

	@Resource
	SysPermissionService sysPermissionService;

	/**
	 * 保存初始化配置文件中的基础项
	 */
	private static Set<String> filterChainDefinitionNames;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lol.common.shiro.service.DynamicPermissionService#init()
	 */
	@PostConstruct
	public synchronized void init() {
		reloadPermission();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lol.common.shiro.service.DynamicPermissionService#
	 * reloadPermission()
	 */
	public synchronized void reloadPermission() {
		try {
			Map<String, String> pers = loadDynamicPermission();
			DefaultFilterChainManager manager = getFilterChainManager();
			if (filterChainDefinitionNames == null) {
				filterChainDefinitionNames = new HashSet<String>(manager.getChainNames());
			} else {
				for (String name : filterChainDefinitionNames) {
					logger.debug("---perms-init---:" + name);
				}
			}
			addToChain(manager, pers);
		} catch (Exception e) {
			logger.error("reload资源权限配置发生错误！", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.lol.common.shiro.service.DynamicPermissionService#
	 * updatePermission(java.util.Map)
	 */
	public synchronized void updatePermission(Map<String, String> newDefinitions) {
		try {
			// 获取和清空初始权限配置
			DefaultFilterChainManager manager = getFilterChainManager();
			addToChain(manager, newDefinitions);
		} catch (Exception e) {
			logger.error("更新资源权限配置发生错误!", e);
		}
	}

	/**
	 * 获取filter管理器
	 * 
	 * @return
	 * @throws Exception
	 */
	private DefaultFilterChainManager getFilterChainManager() throws Exception {
		PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) shiroFilter
				.getFilterChainResolver();
		DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
		return manager;
	}

	/**
	 * 添加指定filter配置列表
	 * 
	 * @param manager
	 * @param definitions
	 * @throws Exception
	 */
	private void addToChain(DefaultFilterChainManager manager, Map<String, String> definitions) throws Exception {
		if (definitions == null || CollectionUtils.isEmpty(definitions)) {
			return;
		}
		delToChain(manager);
		for (Map.Entry<String, String> entry : definitions.entrySet()) {
			String path = entry.getKey();
			String chainDefinition = entry.getValue().trim().replace(" ", "");
			manager.createChain(path, chainDefinition);
			//logger.debug("---perms---add---:" + path + "   " + chainDefinition);
		}
	}

	/**
	 * 移除原有配置（不包括配置文件中的基础配置）
	 * 
	 * @param manager
	 */
	private void delToChain(DefaultFilterChainManager manager) {
		// 移除/**的过滤器链，防止新加的权限不起作用。
		manager.getFilterChains().remove("/**");
		Set<String> chainNames = new HashSet<String>(manager.getChainNames());
		// 移除原有配置（不包括配置文件中的基础配置）
		for (String chainName : chainNames) {
			if (!filterChainDefinitionNames.contains(chainName)) {
				manager.getFilterChains().remove(chainName);
				//logger.debug("---perms---del---:" + chainName);
			}
		}

	}

	/**
	 * 加载动态权限配置
	 * 
	 * @return
	 */
	private Map<String, String> loadDynamicPermission() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		SysPermissionExample example = new SysPermissionExample();
		example.createCriteria().andPathIsNotNull().andAppIdEqualTo(Constants.SYS_APP_ID).andStatusEqualTo(0);
		example.setOrderByClause("priority asc");
		List<SysPermission> sysPermissions = sysPermissionService.selectByExample(example);
		if (sysPermissions == null) {
			return map;
		}
		for (SysPermission sysPermission : sysPermissions) {
			String path = sysPermission.getPath();
			String perms = "perms[" + sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate() + "]";
			if (path.endsWith(".action")) {
				map.put(path, perms);
				//System.out.println("---perms---load---:" + path + "   " + perms);
			}
		}
		map.put("/**", "user");
		return map;
	}

}
