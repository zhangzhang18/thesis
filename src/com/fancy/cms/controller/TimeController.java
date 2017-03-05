package com.fancy.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fancy.cms.model.Students;
import com.fancy.cms.model.Time;
import com.fancy.cms.service.TimeService;

@Controller
@RequestMapping("/time")
public class TimeController {

	@Autowired
	private TimeService timeService;

	@RequestMapping("/show.do")
	public String showtime(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Time time = timeService.selectTime();
		model.addAttribute("thesistime", time);
		return "time/timeshow";
	}
	@RequestMapping("/update.do")
	public String update(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "time/updatetime";
	}
	@RequestMapping(value = "/timeupdate.do", method = RequestMethod.POST)
	public String timup(Time time) {

		timeService.update(time);
	
		return "redirect:show.do";
	}
}
