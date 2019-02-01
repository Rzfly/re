package com.shu.re.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shu.re.Model.Course;
import com.shu.re.Model.User;
import com.shu.re.Repository.CourseRepository;
import com.shu.re.Repository.Custom.UserRepositoryImpl;
import com.shu.re.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Userservice {

    @Autowired
    UserRepository userRepository;
    CourseRepository courseRepository;

    public Map<String,String> quartcourse(String uuid)
    {
        User dbuser = userRepository.findByUuid(uuid);
        List<Course> courseList = courseRepository.findAll();
        Map map = new HashMap<String,String>();
        for(int i=0;i<courseList.size();i++){
            Integer score = setscore(dbuser,courseList.get(i));
            map.put(courseList.get(i).getUuid(),score.toString());
        }

        return map;
    }

    public Integer setscore(User user,Course course){

        int score = 100;
        String str = course.getCourseTime();
        int length = str.length();
        JSONObject j = JSONObject.parseObject(user.getLianghua());
        JSONObject shijian = j.getJSONObject("shijian");
//        shijian = changekey(shijian);
        String didian = j.getString("didian");
        if(course.getLocation().equals("本部"))
        {
            if(!didian.equals("benbu"))
            {
                score = score - 50;
                System.out.println("地区不一致！");
            }
        }
        if(course.getLocation().equals("延长"))
        {
            if(!didian.equals("yangchang"))
            {
                score = score - 50;
            }
        }
        if(course.getLocation().equals("jiading"))
        {
            if(!didian.equals("jiading"))
            {
                score = score - 50;
            }
        }
        if(length<=6) {
            score = judge(str,score,shijian);
        }else{
            System.out.println("多字节");
            int index = str.indexOf("周");
            if(index!=-1)
            {
                index = str.indexOf("(");
                String str2 = str.substring(0,index);
                score  = judge(str2,score,shijian);

            }else{
            index = str.indexOf(" ");
            String str2 = str.substring(0,index);
            int score2 = judge(str2,score,shijian);
            str2 = str.substring(index+1);
            score = judge(str2,score,shijian);
            if(score2 < score)
            {
                score = score2;
            }
//            int length2 = str2.length();
//            if(length2==0) {
//                score = 0;
//            }else if(length2 <=4){
//                int index2 = str.indexOf("-");
//                String start = str2.substring(index2-1,index2);
//                String end = str2.substring(index2+1,index2+2);
//                start = changekey2(start);
//                end = changekey2(end);
//                System.out.println(shijian.get(start)+"对比"+start);
//                System.out.println(shijian.get(end)+"对比"+end);
//                if(shijian.get(start).equals("off")){
//                    System.out.println("扣分");
//                    score = score - 25;
//                }
//                if(shijian.get(end).equals("off")){
//                    System.out.println("扣分");
//                    score = score - 25;
//                }
//            }else if(length2 <=6){
//                int index2 = str.indexOf("-");
//                if(index2==2) {
//                    String start = str.substring(index2 - 1, index2);
//                    String end = str.substring(index2 + 1);
//                    start = changekey2(start);
//                    end = changekey2(end);
//                    System.out.println(shijian.get(start)+"对比"+start);
//                    System.out.println(shijian.get(end)+"对比"+end);
//                    if(shijian.get(start).equals("off")){
//                        System.out.println("扣分");
//                        score = score - 25;
//                    }
//                    if(shijian.get(end).equals("off")){
//                        System.out.println("扣分");
//                        score = score - 25;
//                    }
//                }else if(index2 == 3){
//                    String start = str.substring(index2 - 2, index2);
//                    String end = str.substring(index2 + 1);
//                    start = changekey2(start);
//                    end = changekey2(end);
//                    System.out.println(shijian.get(start)+"对比"+start);
//                    System.out.println(shijian.get(end)+"对比"+end);
//                    if(shijian.get(start).equals("off")){
//                        System.out.println("扣分");
//                        score = score - 25;
//                    }
//                    if(shijian.get(end).equals("off")){
//                        System.out.println("扣分");
//                        score = score - 25;
//                        }
//                    }
//                }
            }
        }
        return score;
    }

    public JSONObject changekey(JSONObject jsonObject) {
        JSONArray array = new JSONArray();
        array.add("yi");
        array.add("er");
        array.add("san");
        array.add("si");
        array.add("wu");
        array.add("liu");
        array.add("qi");
        array.add("ba");
        array.add("jiu");
        array.add("shi");
        array.add("shiyi");
        array.add("shier");
        array.add("shisan");
        for(int i = 1;i<=13;i++) {
            jsonObject.put("0" + i, jsonObject.getString(array.get(i - 1).toString()));
            jsonObject.remove(array.get(i - 1).toString());
        }
        return jsonObject;
    }

    public String changekey2(String ori_key) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("1","yi");
        map.put("2","er");
        map.put("3","san");
        map.put("4","si");
        map.put("5","wu");
        map.put("6","liu");
        map.put("7","qi");
        map.put("8","ba");
        map.put("9","jiu");
        map.put("10","shi");
        map.put("11","shiyi");
        map.put("12","shier");
        map.put("13","shisan");
        return map.get(ori_key);
    }

    public Integer judge(String str,int score,JSONObject shijian){
        int length = str.length();
        if(length==0) {
            score = 0;
        }else if(length <=4){
            System.out.println("4字节");
            int index = str.indexOf("-");
            String start = str.substring(index-1,index);
            String end = str.substring(index+1,index+2);
            start = changekey2(start);
            end = changekey2(end);
            System.out.println(shijian.get(start)+"对比"+start);
            System.out.println(shijian.get(end)+"对比"+end);
            System.out.println(shijian.get(start)+"off");
            System.out.println(shijian.get(end)+"off");
            if(shijian.get(start).equals("off")){
                System.out.println("扣分");
                score = score - 25;
            }
            if(shijian.get(end).equals("off")){
                System.out.println("扣分");
                score = score - 25;
            }
        }else if(length <=6){
            System.out.println("5~6字节");
            int index = str.indexOf("-");
            if(index==2) {
                String start = str.substring(index - 1, index);
                String end = str.substring(index + 1);
                start = changekey2(start);
                end = changekey2(end);
                System.out.println(shijian.get(start)+"对比"+start);
                System.out.println(shijian.get(end)+"对比"+end);
                if(shijian.get(start).equals("off")){
                    System.out.println("扣分");
                    score = score - 25;
                }
                if(shijian.get(end).equals("off")){
                    System.out.println("扣分");
                    score = score - 25;
                }
            }else if(index == 3){
                String start = str.substring(index - 2, index);
                String end = str.substring(index + 1);
                System.out.println(shijian.get(start)+"对比"+start);
                System.out.println(shijian.get(end)+"对比"+end);
                if(shijian.get(start).equals("off")){
                    System.out.println("扣分");
                    score = score - 25;
                }
                if(shijian.get(end).equals("off")){
                    System.out.println("扣分");
                    score = score - 25;
                }
            }
        }
        return score;
    }
}
