package com.bihell.controller;

import com.bihell.pojo.UserAccount;
import com.bihell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/userlogin")
    public String Login(HttpServletRequest request, String account, String pwd)
    {
        UserAccount user= userService.getUserByAccount(account);
        if (user!=null && user.getPwd().equals(pwd)){
            request.getSession().setAttribute("useraccount",user);
            return "登录成功";
        }

        return "登录失败";
    }

    @RequestMapping(value = "/userlogout")
    public String logout (HttpServletRequest request){
        HttpSession session=request.getSession();
        session.removeAttribute("useraccount");
        return "退出登录";
    }
}