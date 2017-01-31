package com.niit.collaborationbackend.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class IndexController {

	@RequestMapping("/")
	public String getIndexPage()
	{
		return "index";
		
	}
}
