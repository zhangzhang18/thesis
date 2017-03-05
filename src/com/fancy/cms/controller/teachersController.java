package com.fancy.cms.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.common.page.SimplePage;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.Students;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.RcAdminService;
import com.fancy.cms.service.StudentsService;
import com.fancy.cms.service.TeacherService;
import com.fancy.cms.service.Thesisservice;
import com.fancy.cms.util.Constants;

@Controller
@RequestMapping("/teachers")
public class teachersController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Thesisservice thesisservice;
	@Autowired
	private StudentsService studentsService;

//	@Autowired
//	private RcAdminService rcAdminService;
//	@RequestMapping("/alladmin.do")
//	public String alla(Integer id, HttpServletRequest request,
//			HttpServletResponse response, ModelMap model) {
//		List<RcAdmin> admin = rcAdminService.selectadmin();
//		model.addAttribute("admin", admin);// fasong
//		return "user/admins";
//	}
	@RequestMapping(value = "/sel.do", method = RequestMethod.POST)
	public ModelAndView sel(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String pinyin) {

		Teachers teacher = teacherService.getTeacherByName(pinyin);
		   ModelAndView mav = new ModelAndView();
		    mav.setViewName("user/teachers"); 
		    mav.addObject("teacher", teacher); 
			return  mav;
		
	}
	
	
	@RequestMapping(value = "/addtea.do", method = RequestMethod.POST)
	public String add(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		List<Students> detail = studentsService.tselectall();
		model.addAttribute("detail", detail);// fasong
		return "teacher/students";
	}

	@RequestMapping("/add.do")
	public String add(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model, String stuid) {

		return "user/addtea";
	}

	@RequestMapping("/v_list.do")
	public String all(Integer pageNo,  String teaname,HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Pagination pagination = this.teacherService.getPage(SimplePage.cpn(pageNo), Constants.PAGE_SIZE, teaname);
		model.addAttribute("pagination", pagination);
		return "user/teachers";
	}

	@RequestMapping("/update.do")
	public String update(HttpServletRequest request,
			HttpServletResponse response, ModelMap model, Teachers teacher) {
		model.addAttribute("teacher", teacher);

		return "user/teaupdate";
	}
	@RequestMapping(value = "/teaupdate.do", method = RequestMethod.POST)
	public String stuup(Integer id, HttpServletRequest request,
			HttpServletResponse response, ModelMap model,Teachers teacher) {

		teacherService.updateByPrimaryKey(teacher);
	
		return "redirect:v_list.do";
	}

}
