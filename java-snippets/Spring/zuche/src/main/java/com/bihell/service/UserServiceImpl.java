package com.bihell.service;

import com.bihell.dao.UserAccountMapper;
import com.bihell.dao.UserDetailsMapper;
import com.bihell.dao.UserMapper;
import com.bihell.pojo.User;
import com.bihell.dao.UserPositionMapper;
import com.bihell.pojo.UserAccount;
import com.bihell.pojo.UserDetails;
import com.bihell.pojo.UserPosition;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    UserPositionMapper userPositionMapper;

    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Autowired
    StringRedisTemplate template;

    @Autowired
    UserAccountMapper userAccountMapper;

    static final String KEY_USER_INFO__NAME = "com_bihell_user_info_007_%s";

    @Override
    public List<User> getUser(int age) {
        return userMapper.getUser(age);
    }

    @Override
    public List<UserPosition> getVicinity(BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat) {
        return userPositionMapper.getvicinity(minlng, maxlng, minlat, maxlat);
    }

    @Override
    public List<UserPosition> getvicinitysort(BigDecimal longitude, BigDecimal latitude) {
        return userPositionMapper.getvicinitysort(longitude, latitude);
    }

    @Override
    public List<UserDetails> getUserDetails(){
        return userDetailsMapper.getUserDetails();
    }

    @Override
    public String getUserNameById(Integer uid){
        String userName = "未知用户";
        try {
            userName = template.opsForValue().get(String.format(KEY_USER_INFO__NAME, uid));
            if (Strings.isNullOrEmpty(userName)) {
                // Redis中没有就读数据库
                UserDetails userDetails = getUserDetailsByUid(uid);
                if (userDetails != null && !Strings.isNullOrEmpty(userDetails.getCity())) {
                    userName = userDetails.getCity();
                }
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }

        return userName;

    }

    @Override
    public UserDetails getUserDetailsByUid(int uid){
        return userDetailsMapper.getUserDetailsByUid(uid);
    }

    @Override
    public void setUserNameById(Integer uid, String userName){
        template.opsForValue().setIfAbsent("key", "value");
        //String result = template.execute(new RedisCallback<String>() {
        //    @Override
        //    public String doInRedis(RedisConnection connection) throws DataAccessException {
        //        JedisCommands commands = (JedisCommands) connection.getNativeConnection();
        //        return commands.set(key, "锁定的资源", "NX", "PX", 3000);
        //    }
        //});
        template.opsForValue().set(String.format(KEY_USER_INFO__NAME, uid), userName);
    }

    @Override
    public UserAccount getUserByAccount(String account){
        return userAccountMapper.getUserByAccount(account);
    }

}