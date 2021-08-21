package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
//dependency 
	@Autowired
	private IUserService userService;

	public AdminController() {
		System.out.println("In Ctro of " + getClass().getName() + " " + userService);
	}

	// add new request handling method to list all vendors
	@GetMapping("/list")
	public String showVendors(Model map) {

		System.out.println("in show vendors");
		map.addAttribute("vendor_list", userService.getAllVendors());
		return "/admin/list";// AVN:/WEB-INF/views/admin/list.jsp
	}

	// add new request handling Method for deleting vendor details
	@GetMapping("/delete")
	public String deleteVendorDetails(@RequestParam int vid, RedirectAttributes flashMap) {
		System.out.println("in delete vendor details " + vid);
		// invoke service layer
		flashMap.addFlashAttribute("message", userService.deleteVendorDetails(vid));
		return "redirect:/admin/list";// redirect the client : sends temp redirect resp
	}

	// add req handling Method : for Showing Vendor Req form
	@GetMapping("/add")
	public String showAddNewVendorForm(User vendor)//POJO --> Form Data // model to  view layer
	{
		//SC creates vendor instance (def ctro) and adds the POJO in the Model Map
		System.out.println("in Show Vendor req form "+vendor);
		return "/admin/add";// ActualViewName:/WEB-INF/views/admin/add.jsp
	}
	// add req handling Method : for PROCESSING Vendor Req form
	@PostMapping("/add")
	public String processAddVendorForm(User userDetails,RedirectAttributes flashMap) //Form Data--->POJO
	{
		System.out.println("in PROCESS Vendor req form "+userDetails);//populated POJO bound with Form data
		//service layer call
		flashMap.addFlashAttribute("message",userService.registerVendor(userDetails));
		return "redirect:/admin/list";
	}
}
