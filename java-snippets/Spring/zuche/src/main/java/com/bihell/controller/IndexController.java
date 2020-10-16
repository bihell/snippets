package com.bihell.controller;

import com.bihell.pojo.User;
import com.bihell.service.UserService;
import com.bihell.pojo.UserPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class IndexController {
    @Autowired
    UserService userService;
    @GetMapping("/show")
    public List<User> getUser(int age){
        return userService.getUser(age);
    }

    @RequestMapping("/index")
    public Map<String, String> Index(){
        Map map = new HashMap<String, String>();
        map.put("北京","北方城市");
        map.put("深圳","南方城市");
        return map;
    }

    /**
     * 查找附近的人
     * @param radii 半径距离(单位km)
     * @param lon 经度
     * @param lat 纬度
     * @return
     */
    @GetMapping("/nearby")
    public List<UserPosition> getVicinity(double radii, double lon, double lat){
        double r = 6371;//地球半径千米
        double dis = radii;
        log.info("半径为info:"+dis);
        log.error("半径为error:"+dis);
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(lat*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =lat-dlat;
        double maxlat = lat+dlat;
        double minlng = lon -dlng;
        double maxlng = lon + dlng;

        return userService.getVicinity(BigDecimal.valueOf(minlng), BigDecimal.valueOf(maxlng), BigDecimal.valueOf(minlat), BigDecimal.valueOf(maxlat));
    }

    /**
     * 附近的人排序
     * @param lon 经度
     * @param lat 纬度
     * @return
     */
    @GetMapping("/nearbysort")
    public List<UserPosition> getVicinitySort(double lon, double lat){

        return userService.getvicinitysort(BigDecimal.valueOf(lon), BigDecimal.valueOf(lat));
    }
}