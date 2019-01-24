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
	private int size;//容量
	private int amount;//已选人数
	private String location;//上课校区
	private String xklimit;//0=限制人数，1=不限制人数
	private float rate;
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
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	@Column(name = "amount")
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
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
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}

	@Column(name = "factor")
	public String getFactor() {
		return factor;
	}
	public void setFactor(String factor) {
		this.factor = factor;
	}


}
