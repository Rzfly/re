package com.shu.re.Controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.shu.re.Repository.CourseRepository;
import com.shu.re.Repository.Custom.CourseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import com.shu.re.Model.Course;
import com.shu.re.Utils.NetResult;


@RestController
public class CourseController {

	@Autowired
    CourseRepository courseRepository;
	CourseRepositoryImpl courseRepositoryImpl;

//	@RequestMapping(value = "/manage/initCourses", method = RequestMethod.GET)
//	public @ResponseBody List<Course> initCourses() {
//
//        CourseDAO cdao = new CourseDAO();
//        List<Course> courses = cdao.findAll();
//        for(int i=0;i<courses.size();i++)
//        {
//        	Course c =new Course();
//        	c=courses.get(i);
//        	if(c.getSize()!=0)
//        	{
//        	float t1=c.getAmount(),t2=c.getSize();
//        	float rate=t1/t2;
//        	System.out.println(t1);
//        	System.out.println(t2);
//        	System.out.println(rate);
//        	c.setRate(rate*1000);
//        	cdao.save(c);
//        	}
//        }
//        return courses;
//	}

    @RequestMapping(value = "/manage/getonecourse")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer"})
//    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
    public @ResponseBody NetResult getoneCourse(String uuid) {

        NetResult r = new NetResult();
        System.out.println("getcourseid:"+uuid);
        r.status=0;
//        r.result = courseRepository.getOne("0956d398-4c28-4045-b75a-5276e9aa42fb");
        r.result =  courseRepository.findById("0956d398-4c28-4045-b75a-5276e9aa42fb").get();
        System.out.println(((Course) r.result).getUuid());
        if(courseRepository.getOne("0956d398-4c28-4045-b75a-5276e9aa42fb").equals(courseRepository.findById("0956d398-4c28-4045-b75a-5276e9aa42fb").get()))
        {
            System.out.println("equals!");
        }else {

            System.out.println("notequals!");
        }
//        ((Optional) r.result).orElseGet();
//        Course course = courseRepository.getOne(uuid);
//        r.result = course;
//        Course course2 = (Course) courseRepository.getOne("0956d398-4c28-4045-b75a-5276e9aa42fb");
//        course2.plString();
//        //        System.out.println(course2.toString());
//        Course course3 = (Course) courseRepository.findByUuid("0956d398-4c28-4045-b75a-5276e9aa42fb");
//        course3.plString();
//        r.result = course2;
        return r;
    }

	@RequestMapping(value = "/manage/deleteCourse", method = RequestMethod.POST)
	public @ResponseBody NetResult deleteCourse(String uuid) {

        NetResult r = new NetResult();
        System.out.println("deletecourseid:"+uuid);
        courseRepository.deleteById(uuid);
        r.status=0;
        r.result = "保存成功";
		return r;
	}
	
	@RequestMapping(value = "/manage/getAllcourses", method = RequestMethod.POST)
	public @ResponseBody List<Course> getAllcourses() {
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses.get(0).getCourseName());
        System.out.println(courses.get(1).getCourseName());
        return courses;
	}

    @RequestMapping(value = "/manage/getDividedCourses2", method = RequestMethod.POST)
    public @ResponseBody List<Course> getDividedCourses2(int page, int pagesize) {
        String sqlStr = "SELECT * FROM course c "
                +"ORDER BY rate DESC "
                +"limit "+(page-1)*pagesize+","+pagesize+"";
        System.out.println(sqlStr);
        List<Course> acs= courseRepository.getDividedCourses(sqlStr);
        return acs;
    }

    @RequestMapping(value = "/manage/getDividedCourses", method = RequestMethod.POST)
    public @ResponseBody
    JSONObject getDividedCourses(int page, int pagesize) {
        JSONObject res=new JSONObject();
	    int maxtCount=courseRepository.findByNum("16124400");
        if(maxtCount==0) {
            res.put("status", -1);
            res.put("result", "数据为空");
            return res;
        }
        int  pages=maxtCount%pagesize==0?maxtCount/pagesize:((maxtCount/pagesize)+1);
        res.put("pages", pages);
        res.put("page", page);
        res.put("maxtCount", maxtCount);
        res.put("pagesize", pagesize);
        String sqlStr = "select * from course c limit "+(page-1)*pagesize+","+pagesize+"ORDER BY rate DESC";
        List<Course> acs= courseRepositoryImpl.getDividedCourses(sqlStr);
        res.put("status","0");
        res.put("result", acs);
        return res;
    }

	
	@RequestMapping(value = "/manage/saveCourse", method = RequestMethod.POST)
	public @ResponseBody NetResult saveCourse(
			String uuid,
			String courseName,
		    String courseNum,
			String credits,
			String teacherNum,
			String courseTime,
			String addinfo,
			int size,
			String location,
			String xklimit,
			int rate,
			String factor) {
        Course course = null;
        NetResult r = new NetResult();
        if (uuid.isEmpty()) {
            course = new Course();
        } else {
            course = courseRepository.getOne(uuid);
            if (course == null) {
                r.status = -1;
                r.result = "不存在";
                return r;
            }
        }
        course.setCourseName(uuid);
        course.setCourseName(courseName);
        course.setCourseNum(courseNum);
        course.setCredits(credits);
        course.setTeacherNum(teacherNum);
        course.setCourseTime(courseTime);
        course.setAddinfo(addinfo);
        course.setSize(size);
        course.setAmount(0);
        course.setLocation(location);
        course.setXklimit(xklimit);
        course.setRate(rate);
        course.setFactor(factor);
        courseRepository.save(course);
        r.status=0;
        r.result = "保存成功";
		return r;
	}
	
	@RequestMapping(value = "/manage/createCourse", method = RequestMethod.POST)
	public @ResponseBody NetResult createCourse(
			String courseName,
		    String courseNum,
			String credits,
			String teacherNum,
			String courseTime,
			String addinfo,
			int size,
			String location) {
        Course course = new Course();
        NetResult r = new NetResult();
        course.setCourseName(courseName);
        course.setCourseNum(courseNum);
        course.setCredits(credits);
        course.setTeacherNum(teacherNum);
        course.setCourseTime(courseTime);
        course.setAddinfo(addinfo);
        course.setSize(size);
        course.setAmount(0);
        course.setLocation(location);
        System.out.println(course.getUuid());
        System.out.println(course.getRate());
        System.out.println(course.getFactor());
        courseRepository.save(course);
        r.status=0;
        r.result = "保存成功";
		return r;
	}


    @RequestMapping(value = "/manage/unlikeCourse", method = RequestMethod.POST)
    public @ResponseBody JSONObject getElectedCourses(@RequestParam(value = "courserates[]")Integer[] courserates,
                                                      @RequestParam(value = "rate")float rate,
                                                      @RequestParam(value = "rank")Integer rank){
        int newrank=1;
        int newrate = (int)rate;
        List<Integer> list = Arrays.asList(courserates);
        System.out.println(rate);
        System.out.println(rank);
        System.out.println(list);
        for(int i=0;i<10;i++)
        {
            if(list.get(i) > newrate)
            {
                newrank++;
            }
        }
        newrank+=rank;
//        newrank=newrank-5;
//        newrank+=rank;
//        if(newrank<0)
//            newrank=0;
        //int rankend = newrank+10;
        String sqlStr = "SELECT * FROM course c "
                +"ORDER BY rate DESC "
                +"limit "+newrank+","+10+"";
        JSONObject res= new JSONObject();
        List<Course> acs= courseRepository.getDividedCourses(sqlStr);
        res.put("result",acs);
        res.put("rank",newrank);
        return res;
    }
}
