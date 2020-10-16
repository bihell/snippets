package com.bihell.controller;

import com.bihell.pojo.UserPosition;
import com.bihell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * 1. 使用@Controller 注解，在对应的方法上，视图解析器可以解析return 的jsp,html页面，并且跳转到相应页面。若返回json等内容到页面，则需要加@ResponseBody注解
 *
 * 2. @RestController注解，相当于@Controller+@ResponseBody两个注解的结合，返回json数据不需要在方法前面加@ResponseBody注解了，但使用@RestController这个注解，就不能返回jsp,html页面，视图解析器无法解析jsp,html页面
 */
@Controller
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/mynearby")
    public String myNearby(Model model, double lon, double lat) {
        double r = 6371;//地球半径千米
        double dis = 2; //半径 单位:km
        double dlng = 2 * Math.asin(Math.sin(dis / (2 * r)) / Math.cos(lat * Math.PI / 180));
        dlng = dlng * 180 / Math.PI;//角度转为弧度
        double dlat = dis / r;
        dlat = dlat * 180 / Math.PI;
        double minlat = lat - dlat;
        double maxlat = lat + dlat;
        double minlng = lon - dlng;
        double maxlng = lon + dlng;

        List<UserPosition> list = userService.getVicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat));
        model.addAttribute("myinfo", list);
        return "mynearby";
    }
}