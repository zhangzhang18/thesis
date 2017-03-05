package com.fancy.cms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.model.Thesis;
import com.fancy.cms.service.StudentsService;
import com.fancy.cms.service.TeacherService;
import com.fancy.cms.service.Thesisservice;

import com.fancy.cms.util.UserUtil;
import com.fancy.cms.vo.FileBean;

@Controller
@RequestMapping("/teacher")
public class TeaUploadController extends FilleUploadController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Thesisservice thesisservice;

	@Autowired
	private StudentsService studentsService;

	private static final Log log = LogFactory
			.getLog(FilleUploadController.class);

	@RequestMapping(value = "/teaup.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadAttachment(HttpServletRequest request,
			HttpServletResponse response, FileBean bean, String num, String type)
			throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		Up(map, bean);
		InputStream is;
		String rootPath = bean.getOldImgPath();
		is = new FileInputStream(rootPath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		RcAdmin admin = UserUtil.getUser(request);
		Teachers teacher = teacherService.getTeacherByName(admin.getName());
		System.out.println(teacher.getTid());
		List<Thesis> ts = new ArrayList<Thesis>();
		Date date = new Date();
		for (int x = 0; x < hssfWorkbook.getNumberOfSheets(); x++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(x);
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				Thesis thesis = new Thesis();
				thesis.setCtitle(getValue(hssfRow.getCell(0)));
				thesis.setCtype(getValue(hssfRow.getCell(1)));
				thesis.setCproperty(getValue(hssfRow.getCell(2)));
				thesis.setContent(getValue(hssfRow.getCell(3)));
				thesis.setMessage(getValue(hssfRow.getCell(4)));
				thesis.setCflag(0);
				thesis.setTid(teacher.getTid());
				thesis.setSubmitDate(date);
				ts.add(x, thesis);
				System.out.println(ts.toString());
			}

		}
		thesisservice.fromXls2(ts);
		return map;
	}

}