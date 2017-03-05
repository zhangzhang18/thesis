package com.fancy.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fancy.cms.common.exception.InvalidPasswordException;
import com.fancy.cms.common.exception.UserNotFoundException;
import com.fancy.cms.common.manager.AuthenticationMng;
import com.fancy.cms.common.session.SessionProvider;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.util.Constants;
import com.fancy.cms.util.RequestUtils;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.ImageCaptchaService;
@Controller
@RequestMapping("/login")
public class LoginController {

	private static final Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	private AuthenticationMng authMng;

	@Autowired
	private ImageCaptchaService imageCaptchaService;

	@Autowired
	private SessionProvider session;

	@Autowired
	private SessionProvider sessionProvider;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		String returnUrl = RequestUtils.getQueryParam(request, Constants.RETURN_URL);
		RcAdmin user = (RcAdmin) this.sessionProvider.getAttribute(request, Constants.AUTH_KEY);
		// 存在认证信息
		if (user != null) {
			return "redirect:../welcome/index.do";
		}
		if (!StringUtils.isBlank(returnUrl)) {
			model.addAttribute(Constants.RETURN_URL, returnUrl);
		}
		return "login";
	}

	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		RcAdmin user = (RcAdmin) this.sessionProvider.getAttribute(request, Constants.AUTH_KEY);
		if (null != user) {
			this.sessionProvider.logout(request, response);
		}
		return "redirect:login.do";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String submit(String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		RcAdmin user = new RcAdmin();
		String errors = validateSubmit(username, password, captcha, request, response);
		// String errors = "";
		if (StringUtils.isBlank(errors)) {
			try {
				user = this.authMng.adminLogin(username, password, request, response, this.sessionProvider);
				if (null == user) {
					model.addAttribute("errors", "用户不存在!");
					return "login";
				}
			}
			catch (UserNotFoundException e) {
				model.addAttribute("errors", "用户不存在!");
				return "login";
			}
			catch (InvalidPasswordException e) {
				model.addAttribute("errors", "用户密码错误!");
				return "login";
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("userName", user.getUserName());
		} else {
			// 设置登录失败信息
			model.addAttribute("errors", errors);
			return "login";
		}

		// 判断是否是当日值班员

		return "redirect:../welcome/index.do";
	}

	private String validateSubmit(String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response) {
		if (StringUtils.isBlank(captcha)) {
			return "验证码不能为空";
		}

		try {
			if (!this.imageCaptchaService.validateResponseForID(this.session.getSessionId(request, response), captcha)) {
				return "验证码错误";
			}
		}
		catch (CaptchaServiceException e) {
			LoginController.log.warn("", e);
			return "验证码异常";
		}
		return "";
	}
}
