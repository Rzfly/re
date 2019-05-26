package com.shu.re.Model;

/**
 * @ClassName CourseVO
 * @Description TODO
 * @Author Asus
 * @Date 2019/5/26 14:07
 */
public class CourseVO {
    private String uuid;
    private String course_name;//课程名称
    private String course_num;//课程号
    private String credits;//学分
    private String teacher_num;//教师号
    private String course_time;//上课时间
    private String addinfo;//上课地点
    private Integer size;//容量
    private Integer amount;//已选人数
    private String location;//上课校区
    private String xklimit;//0=限制人数，1=不限制人数
    private Integer rate;
    private String factor;

    public CourseVO(){}

    public CourseVO(String uuid, String course_name, String course_num, String credits, String teacher_num, String course_time, String addinfo, Integer size, Integer amount, String location, String xklimit, Integer rate, String factor) {
        this.uuid = uuid;
        this.course_name = course_name;
        this.course_num = course_num;
        this.credits = credits;
        this.teacher_num = teacher_num;
        this.course_time = course_time;
        this.addinfo = addinfo;
        this.size = size;
        this.amount = amount;
        this.location = location;
        this.xklimit = xklimit;
        this.rate = rate;
        this.factor = factor;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_num() {
        return course_num;
    }

    public void setCourse_num(String course_num) {
        this.course_num = course_num;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public String getTeacher_num() {
        return teacher_num;
    }

    public void setTeacher_num(String teacher_num) {
        this.teacher_num = teacher_num;
    }

    public String getCourse_time() {
        return course_time;
    }

    public void setCourse_time(String course_time) {
        this.course_time = course_time;
    }

    public String getAddinfo() {
        return addinfo;
    }

    public void setAddinfo(String addinfo) {
        this.addinfo = addinfo;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getXklimit() {
        return xklimit;
    }

    public void setXklimit(String xklimit) {
        this.xklimit = xklimit;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }

    public static CourseVO convert(Course course){
        CourseVO courseVO=new CourseVO();
        courseVO.uuid=course.getUuid();
        courseVO.course_name=course.getCourseName();
        courseVO.course_num=course.getCourseNum();
        courseVO.credits=course.getCredits();
        courseVO.teacher_num=course.getTeacherNum();
        courseVO.course_time=course.getCourseTime();
        courseVO.addinfo=course.getAddinfo();
        courseVO.size=course.getSize();
        courseVO.amount=course.getAmount();
        courseVO.location=course.getLocation();
        courseVO.xklimit=course.getXklimit();
        courseVO.rate=course.getRate();
        courseVO.factor=course.getFactor();
        return courseVO;
    }
}
