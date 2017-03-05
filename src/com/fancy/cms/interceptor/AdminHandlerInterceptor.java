package com.fancy.cms.interceptor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.fancy.cms.common.manager.AuthenticationMng;
import com.fancy.cms.common.session.SessionProvider;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.util.Constants;
import com.fancy.cms.util.FilesPros;
import com.fancy.cms.util.UserUtil;

public class AdminHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = AdminHandlerInterceptor.getURI(request);
		// 不在验证的范围内
		if (exclude(uri)) {
			return true;
		}
		// 获得用户
		RcAdmin user = null;
		user = this.authMng.retrieveUserFromSession(this.session, request);

		// 获取用户权限
		Set<String> perms = new LinkedHashSet<String>();
		perms = this.authMng.retrievePermsFromSession(this.session, request);

		// 获取是否为超级管理员

		String isSuper = this.authMng.retrieveIsSuperFromSession(this.session, request);

		// 获取菜单

		// 此时可以为null
		UserUtil.setUser(request, user);
		UserUtil.setPerms(request, perms);
		UserUtil.setSuper(request, isSuper);

		// 用户为null跳转到登陆页面
		if (user == null) {
			response.sendRedirect(getLoginUrl(request));
			return false;
		}
	/*	// 没有访问权限，提示无权限。
		if (!"1".equals(isSuper) && !permistionPass(uri, perms)) {
			request.setAttribute("message", "没有权限");
			System.err.println(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}*/
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		Set<String> perms = UserUtil.getPerms(request);
		RcAdmin user = UserUtil.getUser(request);
		// 不控制权限时perm为null，PermistionDirective标签将以此作为依据不处理权限问题。
		if (user != null && mav != null && mav.getModelMap() != null && mav.getViewName() != null) {
			mav.getModelMap().addAttribute(Constants.PERMISSION_MODEL, perms);
		}
	}

	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 */
	private static String getURI(HttpServletRequest request) throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		// System.out.println(request.getScheme() + "://" +
		// request.getServerName() + ":" + request.getServerPort() + uri);
		String ctxPath = helper.getOriginatingContextPath(request);
		int isDomain = Integer.valueOf(FilesPros.getProper("IS_DOMAIN"));
		int devMode = Integer.valueOf(FilesPros.getProper("DEV_MODE"));
		// 是域名访问，则直接返回url
		if (isDomain == 1) {
			return uri;
		}
		// count=1则取两级目录，如/welcome/index.do
		// count=1则取一级目录，如/index.do
		int start = 0, i = 0, count = devMode;
		if (!StringUtils.isBlank(ctxPath)) {
			count++;
		}
		while (i < count && start != -1) {
			start = uri.indexOf('/', start + 1);
			i++;
		}
		if (start <= 0) {
			throw new IllegalStateException("admin access path not like '/inpics/inpic/...' pattern: " + uri);
		}
		return uri.substring(start);
	}

	private AuthenticationMng authMng;

	private String[] excludeUrls; // 允许的url

	private String returnUrl;

	private SessionProvider session;

	private boolean exclude(String uri) {
		if (this.excludeUrls != null) {
			for (String exc : this.excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	public AuthenticationMng getAuthMng() {
		return this.authMng;
	}

	public String[] getExcludeUrls() {
		return this.excludeUrls;
	}

	private String getLoginUrl(HttpServletRequest request) {
		StringBuilder buff = new StringBuilder();
		String ctx = request.getContextPath();
		if (!StringUtils.isBlank(ctx)) {
			buff.append(ctx);
		}
		return buff.append("/login/login.do").toString();

	}

	public String getReturnUrl() {
		return this.returnUrl;
	}

	public SessionProvider getSession() {
		return this.session;
	}

	/*private boolean permistionPass(String uri, Set<String> perms) {
		String u = null;
		int i;
		for (String perm : perms) {
			if (uri.startsWith(perm)) {
				// 获得最后一个 '/' 的URI地址。
				i = uri.lastIndexOf("/");
				if (i == -1) {
					throw new RuntimeException("uri must start width '/':" + uri);
				}
				// u = uri.substring(i + 1);
				// // 操作型地址被禁止
				// if (u.startsWith("o_")) {
				// return false;
				// }
				return true;
			}
		}
		return false;
	}
*/
	@Autowired
	public void setAuthMng(AuthenticationMng authMng) {
		this.authMng = authMng;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	@Autowired
	public void setSession(SessionProvider session) {
		this.session = session;
	}

}
