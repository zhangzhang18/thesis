package com.fancy.cms.common.manager.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fancy.cms.common.encoder.Md5PwdEncoder;
import com.fancy.cms.common.encoder.PwdEncoder;
import com.fancy.cms.common.exception.InvalidPasswordException;
import com.fancy.cms.common.exception.UserNotFoundException;
import com.fancy.cms.common.manager.AuthenticationMng;
import com.fancy.cms.common.session.SessionProvider;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.service.RcAdminService;
import com.fancy.cms.service.RcMenuService;
import com.fancy.cms.util.Constants;
import com.fancy.cms.vo.RoleMenuVo;
import com.fancy.cms.vo.RoleVo;

@Service
public class AuthenticationMngImpl implements AuthenticationMng {

	private static final Log log = LogFactory.getLog(AuthenticationMngImpl.class);

	@Autowired
	RcAdminService rcAdminService;



	/*
	 * (non-Javadoc) 管理员登陆
	 * @see
	 * com.menue.bj.inpics.core.manager.AuthenticationMng#adminL(java.lang.String
	 * , java.lang.String, javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * com.menue.bj.inpics.session.SessionProvider)
	 */
	@Override
	public RcAdmin adminLogin(String papers, String password, HttpServletRequest request, HttpServletResponse response, SessionProvider session)
	        throws UserNotFoundException, InvalidPasswordException {
		RcAdmin user = new RcAdmin();
		PwdEncoder pwdEncoder = new Md5PwdEncoder();
		// 登录验证由用户名更改为证件号码,By Lishq 2014-03-11
		user = this.rcAdminService.getPasswdByPapers(papers);
		String pwd=new String();
		pwd= this.rcAdminService.getpwdByPapers(papers);
		if (null == user) {
			throw new UserNotFoundException();
		}
		if (!password.equals(pwd)) {
			throw new InvalidPasswordException();
		}
		// 获取用户权限列表
	/*	Set<String> rpSet = new LinkedHashSet<String>();
		Set<RoleMenuVo> menuSet = new LinkedHashSet<RoleMenuVo>();
		String isSuper = "0";
		List<RoleVo> listRole = this.rcAdminService.findListRoleByID(user.getId());
		for (RoleVo rv : listRole) {
			List<RoleMenuVo> rolePerms = this.rcMenuService.findListMenuByID(rv.getRoleId());
			for (RoleMenuVo rp : rolePerms) {
				splitPerms(rpSet, rp.getMenuUri());
				menuSet.add(rp);
			}
			// 是超级管理员
			if (rv.getIsSuper() == 1) {
				isSuper = "1";
			}
		}*/
		session.setAttribute(request, response, Constants.AUTH_KEY, user);
		/*session.setAttribute(request, response, Constants.PERMS_KEY, rpSet);
		session.setAttribute(request, response, Constants.MENU_KEY, menuSet);
		session.setAttribute(request, response, Constants.SUPER_KEY, isSuper);*/

		return user;
	}


	/*
	 * (non-Javadoc)
	 * @see
	 * com.menue.bj.inpics.core.manager.AuthenticationMng#retrievePermsFromSession
	 * (com.menue.bj.inpics.session.SessionProvider,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public String retrieveIsSuperFromSession(SessionProvider session, HttpServletRequest request) {
		String isSuper = (String) session.getAttribute(request, Constants.SUPER_KEY);
		if (isSuper == null) {
			return null;
		}
		return isSuper;
	}



	/*
	 * (non-Javadoc)
	 * @see
	 * com.menue.bj.inpics.core.manager.AuthenticationMng#retrievePermsFromSession
	 * (com.menue.bj.inpics.session.SessionProvider,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public Set<String> retrievePermsFromSession(SessionProvider session, HttpServletRequest request) {
		Set<String> perms = (Set<String>) session.getAttribute(request, Constants.PERMS_KEY);
		if (perms == null) {
			return null;
		}
		return perms;
	}

	/*
	 * (non-Javadoc) 获取管理员session
	 * @see
	 * com.menue.bj.inpics.core.manager.AuthenticationMng#retrieveUserFromSession
	 * (com.menue.bj.inpics.session.SessionProvider,
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public RcAdmin retrieveUserFromSession(SessionProvider session, HttpServletRequest request) {
		RcAdmin user = (RcAdmin) session.getAttribute(request, Constants.AUTH_KEY);
		if (user == null) {
			return null;
		}
		return user;
	}



	private void splitPerms(Set<String> set, String perms) {
		if (perms != null) {
			for (String p : StringUtils.split(perms, ',')) {
				if (!StringUtils.isBlank(p)) {
					set.add(p);
				}
			}
		}
	}

	@Override
	public void storeAuthIdToSession(SessionProvider session, HttpServletRequest request, HttpServletResponse response, String authId) {
		session.setAttribute(request, response, Constants.AUTH_KEY, authId);
	}

}
