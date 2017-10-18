package com.lol.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lol.common.bean.EasyUITree;
import com.lol.common.model.ds0.SysPermission;
import com.lol.common.model.ds1.CustInfo;

/**
 * EasyUI工具类
 * 
 * @author cunxing
 *
 */
public class EasyUIUtils {

	/**
	 * 构建树形结构
	 * 
	 * @param sysPermissions
	 * @param parentId
	 * @return
	 */
	public static List<EasyUITree> buildTree(List<SysPermission> sysPermissions, String parentId) {
		List<EasyUITree> permissions = null;
		for (SysPermission sysPermission : sysPermissions) {
			if (sysPermission.getParentId().equals(parentId)) {
				if (permissions == null) {
					permissions = new ArrayList<EasyUITree>();
				}
				EasyUITree easyUITree = new EasyUITree();
				easyUITree.setId(sysPermission.getId());
				easyUITree.setText(sysPermission.getName());
				easyUITree.setIconCls(sysPermission.getIcon());
				easyUITree.setState("open");
				easyUITree.setChecked(false);

				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("path", sysPermission.getPath());
				attributes.put("permission", sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
				attributes.put("parentId", sysPermission.getParentId());
				attributes.put("priority", sysPermission.getPriority());
				attributes.put("target", sysPermission.getTarget());
				easyUITree.setAttributes(attributes);
				String nextParentId = parentId + "/" + sysPermission.getId();
				List<EasyUITree> subPermissions = buildTree(sysPermissions, nextParentId);
				if (subPermissions != null) {
					easyUITree.setChildren(subPermissions);
				}
				permissions.add(easyUITree);
			}
		}
		return permissions;
	}

	/**
	 * 构建树形结构（包含选择项）
	 * 
	 * @param sysPermissions
	 * @param hasPerms
	 * @param parentId
	 * @return
	 */
	public static List<EasyUITree> buildCheckedTree(List<SysPermission> sysPermissions, List<Long> hasPerms,
			String parentId) {
		List<EasyUITree> permissions = null;
		for (SysPermission sysPermission : sysPermissions) {
			if (sysPermission.getParentId().equals(parentId)) {
				if (permissions == null) {
					permissions = new ArrayList<EasyUITree>();
				}
				EasyUITree easyUITree = new EasyUITree();
				easyUITree.setId(sysPermission.getId());
				easyUITree.setText(sysPermission.getName());
				easyUITree.setIconCls(sysPermission.getIcon());
				easyUITree.setState("open");
				if (hasPerms.contains(sysPermission.getId())) {
					easyUITree.setChecked(true);
				} else {
					easyUITree.setChecked(false);
				}

				Map<String, Object> attributes = new HashMap<String, Object>();
				attributes.put("path", sysPermission.getPath());
				attributes.put("permission", sysPermission.getPermsResource() + ":" + sysPermission.getPermsOperate());
				attributes.put("parentId", sysPermission.getParentId());
				attributes.put("priority", sysPermission.getPriority());
				attributes.put("target", sysPermission.getTarget());
				easyUITree.setAttributes(attributes);
				String nextParentId = parentId + "/" + sysPermission.getId();
				List<EasyUITree> subPermissions = buildCheckedTree(sysPermissions, hasPerms, nextParentId);
				if (subPermissions != null) {
					easyUITree.setChildren(subPermissions);
				}
				permissions.add(easyUITree);
			}
		}
		return permissions;
	}

	/**
	 * 构建管辖分店的选项数据列表
	 * 
	 * @param custInfos
	 * @param hasSubCustIds
	 * @return
	 */
	public static List<EasyUITree> buildCheckedSubCustTree(List<CustInfo> custInfos, List<String> hasSubCustIds) {
		List<EasyUITree> subCusts = new ArrayList<EasyUITree>();
		for (CustInfo cust : custInfos) {
			EasyUITree easyUITree = new EasyUITree();
			easyUITree.setId(cust.getId());
			easyUITree.setText(cust.getCustName());
			String custId = String.valueOf(cust.getId());
			if (hasSubCustIds.contains(custId)) {
				easyUITree.setChecked(true);
			} else {
				easyUITree.setChecked(false);
			}
			subCusts.add(easyUITree);
		}
		return subCusts;
	}

}
