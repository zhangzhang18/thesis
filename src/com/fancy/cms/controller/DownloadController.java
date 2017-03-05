package com.fancy.cms.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fancy.cms.util.FilesPros;

@Controller
@RequestMapping("/teacher")
public class DownloadController {

	
    @RequestMapping("download")  
    public void downLoad(HttpServletRequest request, HttpServletResponse response) throws IOException {  
     String file_name = request.getParameter("fileName");  
     System.out.println("-------------->"+file_name);  
     if (!"".equals(file_name)) {  
      String path = FilesPros.getProper("IMAGE_DIRECTORY")+ file_name;  
      File file = new File(path);  
      if (file.exists()) {  
       System.out.println(file_name);  
       InputStream inputStream = null;  
       OutputStream outputStream = null;     
       // 以流的形式下载文件  
       try {  
           inputStream = new BufferedInputStream( new FileInputStream(file));  
           outputStream = new BufferedOutputStream(response.getOutputStream());  
           byte[] buffer = new byte[inputStream.available()];  
           inputStream.read(buffer);  
           inputStream.close();  
           // 清空response  
           response.reset();  
           // 设置response的Header  
           response.addHeader("Content-Disposition","attachment;filename=" + file_name);  
           response.addHeader("Content-Length", "" + file.length());  
           response.setContentType("application/octet-stream");  
           outputStream.write(buffer);  
           outputStream.flush();  
       } catch (Exception e) {  
           // TODO: handle exception  
       }finally{  
           inputStream.close();  
           outputStream.close();  
       }  
         
       System.out.println("------------->文件下载成功！！！");  
      } else {  
       System.out.println("------------->该文件不存在！！！");  
      }  
     } else {  
      System.out.println("------------->下载文件时参数错误！！！");  
     }  
    }  
}
