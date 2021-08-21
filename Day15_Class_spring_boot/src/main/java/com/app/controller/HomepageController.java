package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomepageController {
public HomepageController ()
{
	System.out.println("In ctro of"+getClass().getName());
}

@RequestMapping("/")
public String showHomePage()
{
	System.out.println("in show home page");
	return "/index";//AVN :/WEB-INF/views/index.jsp
}
}
