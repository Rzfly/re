package com.shu.re.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {return "home"; }
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String goHome() {return "home"; }

	@RequestMapping(value = "/user/home", method = RequestMethod.GET)
	public String userHome() { return "home"; }
	
	@RequestMapping(value = "/manage/", method = RequestMethod.GET)
	public String manageHome() { return "manage/home"; }

	@RequestMapping(value = "/manage/userlist", method = RequestMethod.GET)
	public String manageuUserlist() { return "manage/userlist"; }

	@RequestMapping(value = "/manage/adduser", method = RequestMethod.GET)
	public String addUser() { return "manage/adduser"; }

	@RequestMapping(value = "/manage/courselist", method = RequestMethod.GET)
	public String manageCourselist() {return "manage/courselist"; }

	@RequestMapping(value = "courselist", method = RequestMethod.GET)
	public String courselist() {return "courselist"; }

	@RequestMapping(value = "/manage/addcourse", method = RequestMethod.GET)
	public String addCourse() {	return "manage/addcourse"; }
}
