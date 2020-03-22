package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.security.securityDitel.UserDetailesImpl;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "hello", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("I'm Spring MVC-SECURITY application");
		messages.add("5.2.0 version by sep'19 ");
		model.addAttribute("messages", messages);
		return "hello";
	}

	@RequestMapping(value = "hello2", method = RequestMethod.GET)
	public String printWelcome2() {
		return "hello2";
	}

	@GetMapping(value = "user2")
	public String getUser(ModelMap modelMap, Authentication authentication) {
		UserDetailesImpl userDetailes = (UserDetailesImpl) authentication.getPrincipal();
		User user = new User(userDetailes.getPassword(), userDetailes.getUsername(), userDetailes.getUser().getId());
		System.out.println(user);
		List<User> users = userService.findAll();
		System.out.println(users + "22222222222222222222222");
		modelMap.addAttribute("userInJDBC", users);
		modelMap.addAttribute("user",user);
		modelMap.addAttribute("example", "dgrdfg");
		int y=0;
		return "seeUser2";

	}
	@GetMapping(value = "user3")
	public ModelAndView getUser34( Authentication authentication) {
		UserDetailesImpl userDetailes = (UserDetailesImpl) authentication.getPrincipal();
		User user = new User(userDetailes.getPassword(), userDetailes.getUsername(), userDetailes.getUser().getId());
		System.out.println(user);
		List<User> users = userService.findAll();
		System.out.println(users + "22222222222222222222222");
		ModelAndView model=new ModelAndView("seeUser2").addObject("userInJDBC", users);
		model.addObject("user",user);
		model.addObject("example", "dgrdfg");
		int y=0;
		return model;

	}
//	@GetMapping("/admin/admin")
//	public ModelAndView getUsers(@RequestParam(required = false, name = "first_name") String firstName) {
//		List<User> users = null;
//		if (firstName != null) {
//			users = userServise.findAllByFirstName(firstName);
//		} else {
//			users = userServise.findAll();
//		}
//		return new ModelAndView("adminPanel").addObject("usersFromDB", users);
//}
@GetMapping(value = "t")
public String getProfilePage(ModelMap modelMap, Authentication authentication) {
	if (authentication == null) {
		return "redirect:/login";
	}
	UserDetailesImpl userDetailes = (UserDetailesImpl) authentication.getPrincipal();
	User user = new User(userDetailes.getPassword(), userDetailes.getUsername(),  userDetailes.getUser().getId());
	System.out.println(user+"3333333333333");
	modelMap.addAttribute("userInJDBC", userService.findAll());
	modelMap.addAttribute("user", user);
	modelMap.addAttribute("example", "dgrdfg");

	return "profile";
}
}