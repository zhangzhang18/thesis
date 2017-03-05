package com.fancy.cms.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fancy.cms.common.encoder.Md5PwdEncoder;
import com.fancy.cms.common.encoder.PwdEncoder;
import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.common.page.SimplePage;
import com.fancy.cms.model.Students;
import com.fancy.cms.service.StudentsService;
import com.fancy.cms.util.Constants;
import com.fancy.cms.util.DateUtil;
import com.fancy.cms.util.ResponseUtils;


@Controller
@RequestMapping("/students")
public class StudentsController {

	PwdEncoder pwdEncoder = new Md5PwdEncoder();

	@Autowired
	private StudentsService studentsService;

/*	@RequestMapping(value = "/a_check.do", method = RequestMethod.GET)
	public void check(String stuid, String edit, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		int back = 0;
		if (edit == null) {
			Integer count = studentsService.checkPapers(papers);
			if (count != null && count > 0) {
				back = 1;
			}
		} else {
			Integer count = studentsService.checkPapersByEditId(edit, papers);
			if (count != null && count > 0) {
				back = 1;
			}
		}
		ResponseUtils.renderJson(response, back + "");
	}*/
	
	
	@RequestMapping(value = "/studel.do")
	public String del(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer stuid) {
		studentsService.deleteByPrimaryKey(stuid);
		return "redirect:v_list.do";
	}

	@RequestMapping(value = "/addstu.do", method = RequestMethod.POST)
	public String add(String root, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Students record) {
		studentsService.insert(record);
		return "redirect:v_list.do";
	}

	@RequestMapping("/add.do")
	public String add(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String stuid) {

		return "user/addstu";
	}
	@RequestMapping("/v_list.do")
	public String slist(Integer pageNo,  String stuname,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Pagination pagination = this.studentsService.getPage(SimplePage.cpn(pageNo), Constants.PAGE_SIZE, stuname);
		model.addAttribute("pagination", pagination);

		return "user/students";
	}


	@RequestMapping("/students.do")
	public String detail(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String stuid) {

		return "user/students";
	}
//list  
	@RequestMapping(value = "/sel.do", method = RequestMethod.POST)
	public ModelAndView sel(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Integer stuid) {

		List<Students> students = studentsService.selectBystuIdlist(stuid);
		   ModelAndView mav = new ModelAndView();
		    mav.setViewName("user/students"); 
		    mav.addObject("detail", students); 
			return  mav;
		
	}

	@RequestMapping("/update.do")
	public String update(HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Students student) {
		model.addAttribute("student", student);
		
		return "user/stuupdate";
	}
	@RequestMapping(value = "/stuupdate.do", method = RequestMethod.POST)
	public String stuup(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Students student) {

		studentsService.update(student);
	
		return "redirect:v_list.do";
	}

	

}
