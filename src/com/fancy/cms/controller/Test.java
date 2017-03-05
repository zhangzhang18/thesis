package com.fancy.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class Test {
	@RequestMapping("/test.do")
	public String test(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/test";
	}

	// 用户列表
	@RequestMapping("/ulist.do")
	public String user(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/userlist";
	}

	// 贫困学生列表
	@RequestMapping("/pklist.do")
	public String pkuser(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/pklist";
	}

	// 市区列表
	@RequestMapping("/sqlist.do")
	public String shiqu(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/sqlist";
	}

	// 学校列表
	@RequestMapping("/sclist.do")
	public String school(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/sclist";
	}

	// 年纪列表
	@RequestMapping("/grlist.do")
	public String grade(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/grlist";
	}

	// 学校列表
	@RequestMapping("/cllist.do")
	public String classs(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/cllist";
	}

	// 学校列表
	@RequestMapping("/jylist.do")
	public String jy(HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		return "test/jylist";
	}
}
