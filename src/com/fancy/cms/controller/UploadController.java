package com.fancy.cms.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fancy.cms.util.FilesPros;
import com.fancy.cms.vo.FileBean;

@Controller
@RequestMapping("/upload")
public class UploadController {

	// 上传文件的保存路径
	protected String configPath = "upload/widget";

	protected String dirTemp = "upload/widget/temp";

	protected String dirName = "file";

	private static final Log log = LogFactory.getLog(UploadController.class);

	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	@ResponseBody
	public void uploadAttachment(HttpServletRequest request, HttpServletResponse response, FileBean bean) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		// 文件保存目录路径
		String savePath = FilesPros.getProper("IMAGE_DIRECTORY");
		// String savePath = this.getServletContext().getRealPath("/") +
		// configPath;

		// 临时文件目录
		String tempPath = request.getSession().getServletContext().getRealPath("/") + dirTemp;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ymd = sdf.format(new Date());
		savePath += "/" + ymd + "/";
		// 创建文件夹
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		tempPath += "/" + ymd + "/";
		// 创建临时文件夹
		File dirTempFile = new File(tempPath);
		if (!dirTempFile.exists()) {
			dirTempFile.mkdirs();
		}

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// List items = multipartRequest.getFiles("file");
		Map<String, MultipartFile> filemap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : filemap.entrySet()) {
			MultipartFile mf = entity.getValue();
			System.out.println(mf);
			String fimename = mf.getOriginalFilename();
			File file = new File(savePath, fimename);
			FileCopyUtils.copy(mf.getBytes(), file);
		}

		out.flush();
		out.close();
	}
}
