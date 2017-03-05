package com.fancy.cms.common.manager;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fancy.cms.common.exception.InvalidPasswordException;
import com.fancy.cms.common.exception.UserNotFoundException;
import com.fancy.cms.common.session.SessionProvider;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.vo.RoleMenuVo;

/**
 * @author rc.wys
 * @date 2013-5-27 下午4:53:12
 * @DESC 认证信息管理接口
 */
public interface AuthenticationMng {

	/**
	 * @DESC 管理员登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public RcAdmin adminLogin(String username, String password, HttpServletRequest request, HttpServletResponse response, SessionProvider session)
	        throws UserNotFoundException, InvalidPasswordException;


	/**
	 * @DESC 后台管理从session中获取是否为超级管理员
	 * @param session
	 * @param request
	 * @return
	 */
	public String retrieveIsSuperFromSession(SessionProvider session, HttpServletRequest request);

	/**
	 * @DESC 后台管理从session中获取菜单
	 * @param session
	 * @param request
	 * @return
	 */

	/**
	 * @DESC 后台管理从session中获取用户权限
	 * @param session
	 * @param request
	 * @return
	 */
	public Set<String> retrievePermsFromSession(SessionProvider session, HttpServletRequest request);

	/**
	 * @DESC 后台管理从session中获取用户
	 * @param session
	 * @param request
	 * @return
	 */
	public RcAdmin retrieveUserFromSession(SessionProvider session, HttpServletRequest request);


	/**
	 * @DESC 存储用户认证ID到session
	 * @param session
	 * @param request
	 * @param response
	 * @param authId
	 */
	public void storeAuthIdToSession(SessionProvider session, HttpServletRequest request, HttpServletResponse response, String authId);

}