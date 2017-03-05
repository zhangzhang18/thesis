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
import com.fancy.cms.util.FilesPros;
import com.fancy.cms.util.ImageUtil;
import com.fancy.cms.util.UserUtil;
import com.fancy.cms.vo.FileBean;

@Controller
@RequestMapping("/teacher")
public class testUP {

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private Thesisservice thesisservice;

	@Autowired
	private StudentsService studentsService;

	private static final Log log = LogFactory
			.getLog(FilleUploadController.class);

	@RequestMapping(value = "/testup.do", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> uploadAttachment(HttpServletRequest request,
			HttpServletResponse response, FileBean bean, String num, String type)
			throws IOException {
		int status = 0;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			/* 文件基础信息 */
			// 生成新文件名称
			bean.setNewImgName(ImageUtil.getNewName(bean));
			// 获取webRoot路径
			String webRootPath = FilesPros.getProper("IMAGE_DIRECTORY");
			// String webRootPath = this.getWebContextPath(request);
			// 获得新文件存储路径
			bean.setNewImgPath(ImageUtil.getNewFilePath(webRootPath, bean));
			// 临时变量用于存储文件存放路径
			bean.setTmpImgPath(bean.getNewImgPath());
			// 设置源文件存储路径
			bean.setOldImgPath(ImageUtil.getOldFilePath(bean));
			System.out.println("------------------------1"
					+ bean.getNewImgPath());
			if (bean.getUploadFile() != null
					&& bean.getUploadFile().getSize() != 0) {
				/* 生成文件及文件夹操作 */
				System.out.println("------------------------2"
						+ bean.getNewImgPath());
				System.out.println("------------------------3"
						+ bean.getOldImgPath());
				System.out.println("------------------------4"
						+ bean.getUploadFile().getOriginalFilename());
				status = ImageUtil.imageCreate(bean.getNewImgPath(),
						bean.getOldImgPath(), bean.getUploadFile());
				System.out.println("status" + status);
				if (status != 0) {
					map.put("success", false);
					map.put("attachmentPath", "");
					map.put("attachmentName", "");
				} else {
					map.put("success", true);
					map.put("attachmentPath", getAttachmentURLPath(bean));
					map.put("attachmentName", bean.getUploadFile()
							.getOriginalFilename());
				}
			}
			map.put("serverpath", getImgURLPath(bean));
			map.put("error", "");
			map.put("msg", "上传成功!");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("error", "上传失败!");
		}
		InputStream is;
		String rootPath = bean.getOldImgPath();
		is = new FileInputStream(rootPath);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		RcAdmin admin = UserUtil.getUser(request);
		Teachers teacher = teacherService.getTeacherByName(admin.getName());
		System.out.println(teacher.getTid());
		List<Thesis> ts= new ArrayList<Thesis>();
		Date date=new Date();
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

	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getImgURLPath(FileBean bean) {
		String dir = "zhang";
		System.out.println(bean.getOldImgPath() + "111111111111111");
		return bean.getOldImgPath().substring(
				bean.getOldImgPath().indexOf(dir) - 1);
	}

	private String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			// 返回布尔类型的值
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			// 返回数值类型的值
			return Float
					.valueOf(String.valueOf(hssfCell.getNumericCellValue()))
					.intValue()
					+ "";
		} else {
			// 返回字符串类型的值
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}

	/**
	 * @DESC 获得文件的URL访问路径
	 * @param bean
	 * @return
	 */
	public String getAttachmentURLPath(FileBean bean) {
		String dir = bean.getImgSubDirName();
		String tmp = bean.getOldImgPath().substring(0,
				bean.getOldImgPath().lastIndexOf('.'));
		return tmp.substring(tmp.indexOf(dir) - 1);
	}

	public String getAttachmentServerPath(FileBean bean) {
		String url = bean.getImgUrl();
		String dir = bean.getImgSubDirName();
		url = url
				+ bean.getOldImgPath().substring(
						bean.getOldImgPath().indexOf(dir) - 1,
						bean.getOldImgPath().length());
		return url;
	}
}