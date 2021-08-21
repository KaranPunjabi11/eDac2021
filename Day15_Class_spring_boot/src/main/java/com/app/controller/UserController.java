package com.app.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
//dependency : service Layer i/f
	@Autowired //Autowired By type
	private IUserService userService;

	public UserController() {
		System.out.println("In Ctro of " + getClass().getName() + " " + userService);
	}
	@PostConstruct
	public void myinit()
	{
		System.out.println("In Ctro of " + getClass().getName() + " " + userService);
	}

	// add Request handling Method for showing Login Form (show form Phase)
	// key in the HandlerMapping Bean :/user/login:method =get
	// value: com.app.controller.UserController.showLoginForm()
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show Login Form");
		return "/user/login";// actual view name /WEB-INF/Views/user/login.jsp
	}

	// add Request handling Method for PROCESSING Login Form (PROCESS form Phase)
	// key in the HandlerMapping Bean :/user/login:method =POST
	// value: com.app.controller.UserController.processLoginForm(dependency list)
	@PostMapping("/login")
	// SC invokes : String email =request.getParameter("email")
	// SC invokes : String email =request.getParameter("pass")
	public String processLoginForm(@RequestParam String email, @RequestParam(name = "pass") String pwd, Model map,HttpSession session)
	// For Convenience : Match req param with
	{
		System.out.println(" In process login form " + email + " " + pwd);
		try {
			// invoke service Layer Method with dependency
			User validateUser = userService.validateUser(email, pwd);
			// valid Login : add the validated User details under model Map(save in request
			// scope)
			session.setAttribute("user_details", validateUser);
			session.setAttribute("message", "Login Successful under role of " + validateUser.getUserRole());
			if (validateUser.getUserRole().equals(Role.ADMIN))// admin login
				return "redirect:/admin/list";// sends temp redirect // AVN:WEB-INF/Views/admin/list.jsp (if not redirect then only)
			return "redirect:/vendor/details";///AVN: WEB-INF/Views/vendor/details.jsp (if not redirect then only)

		} catch (RuntimeException e) {
			e.printStackTrace();
			// invalid Login --> forward the client to loginjsp
			map.addAttribute("message", "invalid login Pls try again");
			return "/user/login";// Actual View name : /WEB-INF/Views/user/login.jsp
		}

	}
	
	//add new request Handling Method for logging out the user
	@GetMapping("/logout")
	public String userLogout(HttpSession session,Model modelMap,HttpServletRequest request,HttpServletResponse resp)
	{
		System.out.println("in user's logout");
		//access user details from session scope and add them in the model map
		modelMap.addAttribute("details",session.getAttribute("user_details"));
		//discard session
		session.invalidate();
		resp.setHeader("refresh", "5;url="+request.getContextPath());///spring-mvc-boot
		return "/user/logout"; // LVN-->// Actual View name : /WEB-INF/Views/user/logout.jsp (sever pull)
	}
}
