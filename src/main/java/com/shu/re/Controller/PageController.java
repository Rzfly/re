package com.shu.re.Controller;

import com.shu.re.Model.Course;
import com.shu.re.Model.User;
import com.alibaba.fastjson.JSONObject;
import com.shu.re.Repository.CourseRepository;
import com.shu.re.Repository.UserRepository;
import com.shu.re.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

	@Autowired
	UserRepository userRepository;
    @Autowired
	CourseRepository courseRepository;
    @Autowired
    Userservice userservice;

	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String login() {return "login"; }

	@RequestMapping(value = {"/index","/user/home",}, method = RequestMethod.GET)
	public String home() {return "home"; }
	
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


	@RequestMapping(value = "/manage/edituser", method = RequestMethod.GET)
	public String goManageedituser(String id, Model model) {

		User user = new User();
		try {
			user = userRepository.findByUuid(id);
		}catch (Exception e){
			System.out.println(e);
		}
		model.addAttribute("user", JSONObject.toJSON(user));
//		try {
            Course course = courseRepository.findById("17a22068-4b02-43f8-81d3-7e6bdb4c3860").get();
            Integer i = userservice.setscore(user,course);
            System.out.println("score:"+i.toString());
//		}catch (Exception e){
//		    Course course = courseRepository.findByUuid("0e1265a2-50d5-4815-b91f-6ae12fd45f43");
//            Integer i = userservice.setscore(user,course);
//            System.out.println("score:"+i.toString());
//		}
        //        JSONObject object = courseRepository.findById("0e1265a2-50d5-4815-b91f-6ae12fd45f43");
		return "/manage/edituser";
	}

}
