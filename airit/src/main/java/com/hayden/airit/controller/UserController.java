package com.hayden.airit.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hayden.airit.Dao.LogfileDAO;
import com.hayden.airit.Dao.UserDAO;
import com.hayden.airit.Dao.AdminDAO;
import com.hayden.airit.annotation.UserLoginToken;
import com.hayden.airit.entity.*;
import com.hayden.airit.service.*;
import com.hayden.airit.service.AdminService;
import com.hayden.airit.annotation.AdminLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogfileDAO logfileDAO;
    @Autowired
    private LogfileService logfileService;


    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password)
    {
        //TODO-需要token加密
        JSONObject jsonObject = new JSONObject();
        User user = userDAO.findUserByUsername(username);
        if (user == null) {
            jsonObject.put("message","Admin doesn't existed!");
            return jsonObject;
        } else {
            if (user.getPassword() != null && !user.getPassword().contentEquals(password)) {
                jsonObject.put("message", "Password incorrect!");
                return jsonObject;
            } else {
                String token = tokenService.getToken(user);
                jsonObject.put("token",token);
                jsonObject.put("user",user);
                return jsonObject;
            }

        }
    }
    @GetMapping("/showUserLog")
    @CrossOrigin(origins = "*")
    @UserLoginToken
    @ResponseBody
    public List<Logfile> showUserLog(@RequestParam("userLoginName") String username) {
        User user = userDAO.findUserByUsername(username);
        Integer userId = user.getId();
        return logfileDAO.findLogfileByUser_Id(userId);
    }



}
