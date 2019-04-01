package com.shu.re.km;

import com.shu.re.Model.Course;

public class Datasi extends Point{

    private String userid;
    private Course course;

//    public void setCourseid(String courseid) {
//        this.courseid = courseid;
//    }
//
//    public String getCourseid() {
//        return courseid;
//    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }


    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public Datasi(int id,Course course,String userid, float[] localArray) {
        super(id,localArray);
        if(course == null)
        {
            System.out.println("课程为空！！！");
        }else{
            this.setCourse(course);
        }
        this.setUserid(userid);
    }

    public Datasi(Course course,String userid, float[] localArray) {
        super(localArray);
        if(course == null)
        {
            System.out.println("课程为空！！！");
        }else{
            this.setCourse(course);
        }
        this.setUserid(userid);
    }

    public Datasi(float[] localArray) {
        super(localArray);
        this.course = null;
        this.setUserid("-1");
    }
}
