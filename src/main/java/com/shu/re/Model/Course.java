package com.shu.re.Model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Course {

	private String uuid;
	private String courseName;//课程名称
	private String courseNum;//课程号
	private String credits;//学分
	private String teacherNum;//教师号
	private String courseTime;//上课时间
	private String addinfo;//上课地点
	private Integer size;//容量
	private Integer amount;//已选人数
	private String location;//上课校区
	private String xklimit;//0=限制人数，1=不限制人数
	private Integer rate;
	private String factor;

	public Course() {
		this.setUuid(UUID.randomUUID().toString());
		this.setRate(0);
	}//自带uuid

	public Course setUuid(String uuid) {
		this.uuid = uuid;
		return this;
	}

	@Id
	@Column(name = "uuid")
	public String getUuid() {
		return uuid;
	}

	@Column(name = "courseName")
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	@Column(name = "courseNum")
	public String getCourseNum() {
		return courseNum;
	}
	public void setCourseNum(String courseNum) {
		this.courseNum = courseNum;
	}

	@Column(name = "credits")
	public String getCredits() {
		return credits;
	}
	public void setCredits(String credits) {
		this.credits = credits;
	}

	@Column(name = "teacherNum")
	public String getTeacherNum() {
		return teacherNum;
	}
	public void setTeacherNum(String teacherNum) {
		this.teacherNum = teacherNum;
	}

	@Column(name = "courseTime")
	public String getCourseTime() {
		return courseTime;
	}
	public void setCourseTime(String courseTime) {
		this.courseTime = courseTime;
	}

	@Column(name = "addinfo")
	public String getAddinfo() {
		return addinfo;
	}
	public void setAddinfo(String addinfo) {
		this.addinfo = addinfo;
	}

	@Column(name = "size")
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}

	@Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Column(name = "location")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "xklimit")
	public String getXklimit() {
		return xklimit;
	}
	public void setXklimit(String xklimit) {
		this.xklimit = xklimit;
	}

	@Column(name = "rate")
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}

	@Column(name = "factor")
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}

	public void plString(){
//		String str =
//			"uuid"			+ ":" +this.getUuid()	+ "//r//n"
//		+	"courseName"	+ ":" +this.getCourseName()	+ "//r//n"
//		+	"courseNum"		+ ":" +this.getCourseNum()	+ "//r//n"
//		+	"credits"		+ ":" +this.getCredits()	+ "//r//n"
//		+	"teacherNum"	+ ":" +this.getTeacherNum()	+ "//r//n"
//		+	"courseTime"	+ ":" +this.getCourseTime()	+ "//r//n"
//		+	"addinfo"		+ ":" +this.getAddinfo()	+ "//r//n"
//		+	"size"			+ ":" +this.getSize().toString()	+ "//r//n"
//		+	"amount"		+ ":" +this.getAmount().toString()	+ "//r//n"
//		+	"location"		+ ":" +this.getLocation()	+ "//r//n"
//		+	"xklimit"		+ ":" +this.getXklimit()	+ "//r//n"
//		+ 	"rate"			+ ":" +this.getRate()	+ "/r/n"
//		+	"factor"		+ ":" +this.getFactor();
        System.out.println("uuid"			+ ":" +this.getUuid());
        System.out.println("courseName"	+ ":" +this.getCourseName());
        System.out.println("courseNum"		+ ":" +this.getCourseNum());
        System.out.println("credits"		+ ":" +this.getCredits());
        System.out.println("teacherNum"	+ ":" +this.getTeacherNum());
        System.out.println("courseTime"	+ ":" +this.getCourseTime()	);
        System.out.println("addinfo"		+ ":" +this.getAddinfo());
        System.out.println("size"			+ ":" +this.getSize().toString());
        System.out.println("amount"		+ ":" +this.getAmount().toString());
        System.out.println("location"		+ ":" +this.getLocation());
        System.out.println("xklimit"		+ ":" +this.getXklimit());
        System.out.println("rate"			+ ":" +this.getRate());
        System.out.println("factor"		+ ":" +this.getFactor());
	}
}
