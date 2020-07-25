package com.hayden.airit.controller;

import com.alibaba.fastjson.JSONObject;
import com.hayden.airit.Dao.*;
import com.hayden.airit.annotation.AdminLoginToken;
import com.hayden.airit.entity.*;
import com.hayden.airit.service.*;

import com.hayden.airit.annotation.ManagerLoginToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/manager")
@CrossOrigin(origins = "*")
public class ManagerController {
    @Autowired
    private ManagerDAO managerDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private LogfileService logfileService;
    @Autowired
    private LogfileDAO logfileDAO;

    @PostMapping("/login")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public Object login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        //TODO-需要token加密
        JSONObject jsonObject = new JSONObject();
        Manager manager = managerDAO.findManagerByUsername(username);
        if (manager == null) {
            jsonObject.put("message","Manager doesn't existed!");
            return jsonObject;
        } else {
            if (manager.getPassword() != null && !manager.getPassword().contentEquals(password)) {
                jsonObject.put("message", "Password incorrect!");
                return jsonObject;
            } else {
                String token = tokenService.getToken(manager);
                jsonObject.put("token",token);
                jsonObject.put("manager",manager);
                return jsonObject;
            }

        }
    }
    @GetMapping("/showUser")
    @CrossOrigin(origins = "*")
    @ManagerLoginToken
    @ResponseBody
    public List<User> showUser(@RequestParam("managerName") String username) {
        Manager manager = managerDAO.findManagerByUsername(username);
        Integer managerId = manager.getId();
        return userDAO.findUserByManager_Id(managerId);
    }

    @GetMapping("/showUserLog")
    @CrossOrigin(origins = "*")
    @ManagerLoginToken
    @ResponseBody
    public List<Logfile> showUserLog(@RequestParam("userId") Integer userId) {
        return logfileDAO.findLogfileByUser_Id(userId);
    }

    @GetMapping("/AddUserLog")
    @CrossOrigin(origins = "*")
    @ManagerLoginToken
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

    @GetMapping("/fixLog")
    @CrossOrigin(origins = "*")
    @ManagerLoginToken
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
    @ManagerLoginToken
    @ResponseBody
    public void DeleteLog(@RequestParam("logId") Integer logId) {
        logfileDAO.deleteById(logId);
    }

}
