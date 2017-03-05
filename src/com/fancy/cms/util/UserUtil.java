package com.fancy.cms.util;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.Students;
import com.fancy.cms.vo.RoleMenuVo;

public class UserUtil {

	/**
	 * 用户菜单key
	 */
	public static final String MENU_KEY = "_menu_key";

	/**
	 * 用户权限key
	 */
	public static final String PERMS_KEY = "_perms_key";

	/**
	 * 是否超级管理员
	 */
	public static final String SUPER_KEY = "_super_key";

	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "_user_key";

	public static final String MOBILE_KEY = "_mobile_key";

	/**
	 * 获得用户菜单
	 * 
	 * @param request
	 * @return
	 */
	public static Set<RoleMenuVo> getMenu(HttpServletRequest request) {
		return (Set<RoleMenuVo>) request.getAttribute(UserUtil.MENU_KEY);
	}

	/**
	 * 获得用户权限
	 * 
	 * @param request
	 * @return
	 */
	public static Set<String> getPerms(HttpServletRequest request) {
		return (Set<String>) request.getAttribute(UserUtil.PERMS_KEY);
	}

	/**
	 * 获得是否为超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static String getSuper(HttpServletRequest request) {
		return (String) request.getAttribute(UserUtil.SUPER_KEY);
	}

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static RcAdmin getUser(HttpServletRequest request) {
		return (RcAdmin) request.getAttribute(UserUtil.USER_KEY);
	}
	public static Students getStudent(HttpServletRequest request) {
		return (Students) request.getAttribute(UserUtil.USER_KEY);
	}

	/**
	 * @DESC 设置用户菜单
	 * @param request
	 * @param menu
	 */
	public static void setMenu(HttpServletRequest request, Set<RoleMenuVo> menu) {
		request.setAttribute(UserUtil.MENU_KEY, menu);
	}

	/**
	 * 设置用户权限
	 * 
	 * @param request
	 * @param user
	 */
	public static void setPerms(HttpServletRequest request, Set<String> perms) {
		request.setAttribute(UserUtil.PERMS_KEY, perms);
	}

	/**
	 * 设置超级管理员标识
	 * 
	 * @param request
	 * @param user
	 */
	public static void setSuper(HttpServletRequest request, String isSuper) {
		request.setAttribute(UserUtil.SUPER_KEY, isSuper);
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, RcAdmin user) {
		request.setAttribute(UserUtil.USER_KEY, user);
	}
	public static void setStudent(HttpServletRequest request, Students student) {
		request.setAttribute(UserUtil.USER_KEY, student);
	}
}
