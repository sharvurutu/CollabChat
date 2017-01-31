package com.niit.collaborationbackend.controller;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.niit.collaborationbackend.util.FileUtil;

@RestController
@MultipartConfig(fileSizeThreshold=20971520)
public class FileUploadController {
	@Autowired(required=false)
	HttpSession session;

	public static Logger log = org.slf4j.LoggerFactory.getLogger(FileUploadController.class);

	@RequestMapping(value="/upload")
	public void uploadFile(@RequestParam("uploadFile") MultipartFile multipartFile)
	{
		String userName = (String) session.getAttribute("loggedInUserId");
		FileUtil.Upload(multipartFile, userName+".jpg");
	}
	
	
}
