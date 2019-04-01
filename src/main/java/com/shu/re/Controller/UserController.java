package com.shu.re.Controller;

import com.shu.re.Model.User;
import com.shu.re.Repository.Custom.UserRepositoryImpl;
import com.shu.re.Repository.UserRepository;
import com.shu.re.Utils.NetResult;
import com.shu.re.Utils.utility;
import com.shu.re.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;
    UserRepositoryImpl userRepositoryImpl;

    @RequestMapping(value = "/manage/saveUser", method = RequestMethod.POST)
    public @ResponseBody
    NetResult saveUser(
            String uuid,
            String name,
            String password,
            String uid,
            int type,
            String lianghua) {
        System.out.println("saveuser!"+uuid);
        System.out.println("saveuser!"+lianghua);
        User user = null;
        NetResult r = new NetResult();
        if (utility.EmptyString(uuid)) {
            user = new User();
        } else {
            user = userRepository.getOne(uuid);
            if (user == null) {
                r.status = -1;
                r.result = "不存在";
                return r;
            }
        }
        user.setUuid(uuid);
        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        user.setUid(uid);
        user.setlianghua(lianghua);
        userRepository.saveAndFlush(user);
        r.status = 0;
        r.result = "保存成功";
        return r;
    }

    @RequestMapping(value = "/manage/createUser", method = RequestMethod.POST)
    public @ResponseBody
    NetResult createUser(
            String name,
            String password,
            String uid,
            int type,
            String lianghua) {
        User user = new User();
        NetResult r = new NetResult();
        user.setName(name);
        user.setPassword(password);
        user.setType(type);
        user.setUid(uid);
//        user.setlianghua(lianghua);
        userRepository.save(user);
        r.status = 0;
        r.result = "保存成功";
        System.out.println(user.getUuid());
        return r;
    }

    @RequestMapping(value = "/manage/getoneuser", method = RequestMethod.POST)
    public @ResponseBody
    NetResult getoneUser(
            String uuid) {
        NetResult r = new NetResult();
        r.status = 0;
        System.out.println(uuid);
        User user = new User();
        user = userRepository.findByUuid(uuid);
        r.result = user;
        System.out.println(user.toString());
        System.out.println(user.getName());
        System.out.println("getone!");
        System.out.println(r.result.toString());
        return r;
    }

    @RequestMapping(value = "/manage/deleteUser", method = RequestMethod.POST)
    public @ResponseBody
    NetResult deleteUser(String uuid) {
        NetResult r = new NetResult();
        userRepository.deleteById(uuid);
        r.status = 0;
        r.result = "删除成功";
        System.out.println(uuid);
        return r;
    }

    @RequestMapping(value = "/manage/getDividedUsers", method = RequestMethod.POST)
    public NetResult getDividedUsers(int page, int pagesize) {
        String sqlStr = "select * from User u limit " + (page - 1) * pagesize + "," + pagesize + "";
        NetResult r = new NetResult();
        r.status = 0;
        r.result = userRepositoryImpl.getDividedUsers(sqlStr);
        return r;
    }

    @RequestMapping(value = "/manage/getAllUsers", method = RequestMethod.POST)
    public List<User> getAllUser() {
        List<User> acs = userRepository.findAll();
        return acs;
    }

}
