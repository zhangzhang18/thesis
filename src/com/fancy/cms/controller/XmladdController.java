package com.fancy.cms.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fancy.cms.model.RcAdmin;
import com.fancy.cms.model.Students;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.service.RcAdminService;
import com.fancy.cms.service.StudentsService;
import com.fancy.cms.service.TeacherService;
import com.fancy.cms.service.Thesisservice;
import com.fancy.cms.vo.FileBean;

@Controller
@RequestMapping("/xmladd")
public class XmladdController extends FilleUploadController {

	@Autowired
	private RcAdminService rcAdminService;
	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Thesisservice thesisservice;

	@Autowired
	private StudentsService studentsService;

	private static final Log log = LogFactory
			.getLog(FilleUploadController.class);

	@RequestMapping(value = "/students.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadAttachment(HttpServletRequest request,
			HttpServletResponse response, FileBean bean, String num, String type)
			throws IOException {

		Map<String, Object> map = new HashMap<String, Object>();
		Up(map, bean);
		InputStream is;
		String rootPath = bean.getOldImgPath();
		is = new FileInputStream(rootPath);
		List<Students> students = new ArrayList<Students>();
		List<Teachers> teachers = new ArrayList<Teachers>();
		List<RcAdmin> rcAdmins = new ArrayList<RcAdmin>();
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		for (int x = 0; x < hssfWorkbook.getNumberOfSheets(); x++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(x);
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow h0 = hssfSheet.getRow(0);
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (getValue(h0.getCell(0)).equals("stuid")) {
					Students student = new Students();
					RcAdmin rcAdmin = new RcAdmin();
					Float stuclass = Float
							.valueOf(getValue(hssfRow.getCell(4)));
					Float tid = Float.valueOf(getValue(hssfRow.getCell(5)));
					student.setStuid(Integer.parseInt(getValue(hssfRow
							.getCell(0))));
					student.setStuname(getValue(hssfRow.getCell(1)));
					student.setStusex(getValue(hssfRow.getCell(2)));
					student.setStupwd(getValue(hssfRow.getCell(3)));
					student.setStuclass(stuclass.intValue());
					student.setTid(tid.intValue());
					rcAdmin.setUsername(getValue(hssfRow.getCell(1)));
					rcAdmin.setPapers(getValue(hssfRow.getCell(0)));
					rcAdmin.setName(getValue(hssfRow.getCell(1)));
					rcAdmin.setPassWord(getValue(hssfRow.getCell(3)));
					rcAdmin.setRole_id(3);
					rcAdmins.add(rcAdmin);
					students.add(student);
				} else if (getValue(h0.getCell(0)).equals("TID")) {
					Float tid = Float.valueOf(getValue(hssfRow.getCell(0)));
					Float ssum = Float.valueOf(getValue(hssfRow.getCell(3)));
					Teachers teacher = new Teachers();
					RcAdmin rcAdmin = new RcAdmin();
					teacher.setTid(tid.intValue());
					teacher.setPinyin(getValue(hssfRow.getCell(2)));
					teacher.setTeaName(getValue(hssfRow.getCell(1)));
					teacher.setBack1(getValue(hssfRow.getCell(4)));
					teacher.setBack2(getValue(hssfRow.getCell(5)));
					teacher.setSsum(ssum.intValue());
					rcAdmin.setName(getValue(hssfRow.getCell(1)));
					rcAdmin.setPapers(getValue(hssfRow.getCell(1)));
					rcAdmin.setUsername(getValue(hssfRow.getCell(1)));
					rcAdmin.setPassWord("123456");
					rcAdmin.setRole_id(2);
					rcAdmins.add(rcAdmin);
					teachers.add(teacher);
				}

			}
		}
		System.out.println(students.size());
		System.out.println(rcAdmins.size());
		System.out.println(teachers.size());
		if (!students.isEmpty()) {
			studentsService.fromXls2(students);
			rcAdminService.fromXls2(rcAdmins);
		}
		if (!teachers.isEmpty()) {
			teacherService.fromXls2(teachers);
			rcAdminService.fromXls2(rcAdmins);
		}
		return map;
	}

}