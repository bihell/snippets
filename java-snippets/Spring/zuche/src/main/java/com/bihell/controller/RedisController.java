package com.bihell.controller;

import com.bihell.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

@RestController
@Slf4j
public class RedisController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getusernamebyid")
    public String getUserNameById(Integer uid)
    {
        return userService.getUserNameById(uid);
    }

    @RequestMapping(value = "/setusernamebyid")
    public String setUserNameById(Integer uid, String uname)
    {
        userService.setUserNameById(uid, uname);
        return "设置成功";
    }

    @RequestMapping(value = "/jedistest")
    public String jedisTest(){
        // 创建一个jedis对象
        Jedis jedis = new Jedis("ip", 6379);
        // 直接调用jedis对象的方法，方法名称和redis的命令一致
        jedis.set("key1", "test01");
        String key1 = jedis.get("key1");
        System.out.println(key1 + " " + key1);
        // 关闭jedis
        jedis.close();
        return key1;
    }
}