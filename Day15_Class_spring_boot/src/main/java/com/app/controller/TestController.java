package com.app.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // mandatory
@RequestMapping("/test") // optional BUT Recommended
public class TestController {

	public TestController() {
		System.out.println("In Ctro of " + getClass().getName());
	}

//mandatory GetMapping 
	@GetMapping("/test1") // =@RequestMapping : method =GET
	public ModelAndView testme() {
		System.out.println("in test me");
		// return Model AND View object wrapping logical view nm +model attribute
		// o.s.w.s ModelAndView(String viewName,String modelAttrName,Object value)
		return new ModelAndView("/test/test1", "server_date", LocalDateTime.now());
		// Handler return M&V to the Dispatcher servlet
		// Logical View name : /test/test1,actual view name reted BY V.R(view resolver)
		// WEB-INF/views/test/test1.jsp
	}

	// add req handling method for storing results in the model map
	@GetMapping("/test2")
	//o.s.ui.Model : i/f
	//it represent holder for Model attributes 
	public String testModelMap(Model modelMap) {
		System.out.println("in test model Map" + modelMap);// empty map:{}
		modelMap.addAttribute("server_date", LocalDateTime.now()).addAttribute("num_list",
				Arrays.asList(10, 20, 30, 40));
		System.out.println("in test model Map" + modelMap); // populated with 2 model attributes
		return "/test/test1";// handler rets EXPLICITYLY : logic view name to F.C
		// handler IMPLICITYLY RETS ENTIRE MODEL MAP TO F.C
	}
}
