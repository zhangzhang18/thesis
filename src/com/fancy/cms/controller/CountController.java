package com.fancy.cms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fancy.cms.model.Students;
import com.fancy.cms.model.Teachers;
import com.fancy.cms.model.Thesis;
import com.fancy.cms.service.StudentsService;
import com.fancy.cms.service.TeacherService;
import com.fancy.cms.service.Thesisservice;
import com.fancy.cms.util.DownLoadUtil;
import com.fancy.cms.util.FilesPros;

@Controller
@RequestMapping("/count")
public class CountController {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Thesisservice thesisservice;

	@Autowired
	private StudentsService studentsService;

	@RequestMapping(value = "/xml.do")
	public void downLoad(HttpServletRequest request,
			HttpServletResponse response) {
		List<Thesis> thesis = thesisservice.selectall();
		String[] title = { "姓名", "学号", "性别", "班级", "指导教师", "论文题目" };
		// 创建Excel工作簿
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 创建一个工作表sheet
		XSSFSheet sheet = workbook.createSheet();
		// 创建第一行
		Row row = sheet.createRow(0);
		Cell cell = null;
		// 插入第一行数据 id,name,sex

		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
		}
		int rowNum = 1;
		for (int i = 0; i < thesis.size(); i++) {
			if (thesis.get(i).getStuid() != null) {
				String stuname = studentsService.selectBystuId(
						thesis.get(i).getStuid()).getStuname();
				thesis.get(i).setStuname(stuname);
				Students student = studentsService.selectBystuId(thesis.get(i)
						.getStuid());
				Teachers teachers = teacherService.getTeacherByCTid(thesis.get(
						i).getTid());

				if (student.getTid() == null) {
					break;
				} else {
					row = sheet.createRow(rowNum++);
					// Row nextrow = sheet.createRow(i);
					Cell cell2 = row.createCell(0);
					cell2.setCellValue(thesis.get(i).getStuname());
					cell2 = row.createCell(1);
					cell2.setCellValue(thesis.get(i).getStuid());
					cell2 = row.createCell(2);
					cell2.setCellValue(student.getStusex());
					cell2 = row.createCell(3);
					cell2.setCellValue(student.getStuclass());
					cell2 = row.createCell(4);
					cell2.setCellValue(teachers.getTeaName());
					cell2 = row.createCell(5);
					cell2.setCellValue(thesis.get(i).getCtitle());
				}

			}
		}
		// 列总和计算
		row = sheet.createRow(rowNum);
		row.createCell(0).setCellValue("TOTAL:");
		row.createCell(1).setCellValue(rowNum - 1);
		// 创建一个文件
		String path = FilesPros.getProper("XML_DIRECTORY") + "thesis.xlsx";
		String fileName = "thesis.xlsx";
		File file = new File(path);
		try {
			response.setContentType("application/msexcel;charset=GBK");
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String(fileName.getBytes(), "ISO8859-1") + "\"");
			response.setContentLength((int) file.length());
			byte[] buffer = new byte[4096];
			BufferedOutputStream output = null;
			BufferedInputStream input = null;
			try {
				output = new BufferedOutputStream(response.getOutputStream());
				input = new BufferedInputStream(new FileInputStream(file));
				int n = -1;
				while ((n = input.read(buffer, 0, 4096)) > -1) {
					output.write(buffer, 0, n);
				}
				output.flush();
				response.flushBuffer();
			} catch (Exception e) {
			} finally {
				if (input != null)
					input.close();
				if (output != null)
					output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
