package com.pepple.webapp.controller;

import com.pepple.webapp.pojo.User;
import com.pepple.webapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: pepple
 * @date: 2017/4/10 15:02
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BaseController{
    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(User user) {
        logger.info("user.id = " + user.getId() + ", user.name = " + user.getName());
        logger.debug("debug---user.id = " + user.getId() + ", user.name = " + user.getName());
        return "login-index";
    }

    @ResponseBody
    @RequestMapping("/getuser")
    public String login(long id) {
        logger.info("id = " + id);
        User user = userService.getUserById(id);
        if(user != null) {
            logger.info("get user from db... user.id = " + user.getId() + ", user.name = " + user.getName());
        }
        else {
            logger.error("error get user from db");
        }
        return "success";
    }
}
