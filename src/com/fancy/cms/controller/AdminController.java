package com.fancy.cms.controller;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fancy.cms.common.encoder.Md5PwdEncoder;
import com.fancy.cms.common.encoder.PwdEncoder;
import com.fancy.cms.common.page.Pagination;
import com.fancy.cms.common.page.SimplePage;
import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.RcRole;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.RcAdminService;
import com.fancy.cms.service.RcRoleService;
import com.fancy.cms.util.Constants;
import com.fancy.cms.util.DateUtil;
import com.fancy.cms.util.ResponseUtils;
import com.fancy.cms.util.UserUtil;
import com.fancy.cms.vo.RoleVo;

/**
 * 标题、简要说明. <br>
 * 类详细说明. 管理员控制类
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Log log = LogFactory.getLog(AdminController.class);

	PwdEncoder pwdEncoder = new Md5PwdEncoder();

	@Autowired
	private RcAdminService rcAdminService;


	@RequestMapping("/v_main.do")
	public String main() {

		return "admin/main";
	}

	@RequestMapping("/v_left.do")
	public String left() {
		return "admin/left";
	}
	@RequestMapping("/adduser.do")
	public String add() {
		return "user/add";
	}

	@RequestMapping(value = "/v_save.do", method = RequestMethod.POST)
	public String save(RcAdmin rcAdmin, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		
		rcAdminService.addUser(rcAdmin);
		return "redirect:v_list.do";
	}
	
	@RequestMapping("/v_list.do")
	public String all(Integer pageNo,  String papers, HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		Pagination pagination = this.rcAdminService.getPage(SimplePage.cpn(pageNo), Constants.PAGE_SIZE, papers);
		model.addAttribute("pagination", pagination);
		return "user/admins";
	}
	
	
	@RequestMapping("/v_tree.do")
	public String tree(String root, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		RcAdmin user = UserUtil.getUser(request);

		return "admin/tree";
	}


	@RequestMapping(value = "/o_update.do")
	public String update(Integer root, RcAdmin bean, String passWordOri, Integer[] roleId, HttpServletRequest request, ModelMap model) {
		try {
			RcAdmin user = UserUtil.getUser(request);
			// String[] tmpArr = bean.getDeptInfo().split("\\|\\|");
			// if ("0".equals(tmpArr[0])) {
			// tmpArr[0] = String.valueOf(user.getDeptId());
			// }
			// bean.setDeptId(Integer.parseInt(tmpArr[0]));
			// bean.setDeptLevel(Integer.parseInt(tmpArr[1]));
			if (!StringUtils.isBlank(bean.getPassWord())) {
				String encrypt = this.pwdEncoder.encodePassword(bean.getPassWord(), Constants.DEFAULT_SALT);
				bean.setPassWord(encrypt);
			} else {
				bean.setPassWord(passWordOri);
			}
			// model.addAttribute("root", Integer.parseInt(tmpArr[0]));
		}
		catch (Exception e) {
			AdminController.log.error("update RcAdmin id=" + bean.getId());
		}
		AdminController.log.info("update RcAdmin id=" + bean.getId());
		return "redirect:v_list.do";
	}

	@RequestMapping(value = "/a_check.do", method = RequestMethod.GET)
	public void check(String papers, String edit, HttpServletRequest request, HttpServletResponse response, ModelMap model) {

		int back = 0;
		if (edit == null) {
			Integer count = rcAdminService.checkPapers(papers);
			if (count != null && count > 0) {
				back = 1;
			}
		} else {
			Integer count = rcAdminService.checkPapersByEditId(edit, papers);
			if (count != null && count > 0) {
				back = 1;
			}
		}
		ResponseUtils.renderJson(response, back + "");
	}

}
