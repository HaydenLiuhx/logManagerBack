package com.hayden.airit.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hayden.airit.Dao.LogfileDAO;
import com.hayden.airit.Dao.ManagerDAO;
import com.hayden.airit.Dao.UserDAO;
import com.hayden.airit.Dao.AdminDAO;
import com.hayden.airit.annotation.UserLoginToken;
import com.hayden.airit.entity.*;
import com.hayden.airit.service.LogfileService;
import com.hayden.airit.service.TokenService;
import com.hayden.airit.service.AdminService;
import com.hayden.airit.annotation.AdminLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminDAO adminDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LogfileDAO logfileDAO;
    @Autowired
    private AdminService adminService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogfileService logfileService;
    @Autowired
    private ManagerDAO managerDAO;

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //TODO-需要token加密
        JSONObject jsonObject = new JSONObject();
        Admin admin = adminService.findAdminByUsername(username);
        if (admin == null) {
            jsonObject.put("message","Admin doesn't existed!");
            return jsonObject;
        } else {
            if (admin.getPassword() != null && !admin.getPassword().contentEquals(password)) {
                jsonObject.put("message", "Password incorrect!");
                return jsonObject;
            } else {
                String token = tokenService.getToken(admin);
                jsonObject.put("token",token);
                jsonObject.put("admin",admin);
                return jsonObject;
            }

        }

    }
    @GetMapping("/showAllUser")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public List<User> showAllUser() { return userDAO.findAll(); }

    @GetMapping("/showAllLog")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public List<Logfile> showAllLogfile() { return logfileDAO.findAll(); }

    @GetMapping("/showUserLog")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public List<Logfile> showUserLog(@RequestParam("userId") Integer userId) { return logfileDAO.findLogfileByUser_Id(userId); }

    @GetMapping("/AddUserLog")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public Logfile addUserLog(@RequestParam("logInfo") String logInfo,

                              @RequestParam("userId") Integer userId) {
        Logfile logfile = new Logfile();
        logfile.setLogInfo(logInfo);

        Date date = new Date();// 获取当前时间
        logfile.setLogTime(date);
        User user = userDAO.findUserById(userId);
        logfile.setUser(user);
        logfileService.save(logfile);
        return logfile;
    }

    @GetMapping("/AddUser")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public User addUser(@RequestParam("userBalance") Integer userBalance,
                        @RequestParam("userEmail") String userEmail,
                        @RequestParam("userName") String userName,
                        @RequestParam("userPassword") String userPassword,
                        @RequestParam("userPhone") String userPhone,
                        @RequestParam("userLoginName") String userLoginName,
                        @RequestParam("userManager") Integer userManager){
        User user = new User();
        user.setBalance(userBalance);
        user.setEmail(userEmail);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setPhone(userPhone);
        user.setUsername(userLoginName);
        user.setManager(managerDAO.findManagerById(userManager));
        userDAO.save(user);
        return user;
    }

    @GetMapping("/fixLog")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public Logfile fixLog(@RequestParam("logInfo") String logInfo,
                          @RequestParam("logId") Integer logId) {
        Logfile logfile = logfileDAO.findLogfileById(logId);
        logfile.setLogInfo(logInfo);
        Date date = new Date();
        logfile.setLogTime(date);
        logfileDAO.save(logfile);
        return logfile;
    }

    @GetMapping("/DeleteLog")
    @CrossOrigin(origins = "*")
    @AdminLoginToken
    @ResponseBody
    public void DeleteLog(@RequestParam("logId") Integer logId) {
        logfileDAO.deleteById(logId);
    }



}
