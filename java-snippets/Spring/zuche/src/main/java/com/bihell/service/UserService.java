package com.bihell.service;

import com.bihell.pojo.User;
import com.bihell.pojo.UserAccount;
import com.bihell.pojo.UserDetails;
import com.bihell.pojo.UserPosition;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    UserDetails getUserDetailsByUid(int uid);
    String getUserNameById(Integer uid);
    void setUserNameById(Integer uid, String userName);
    List<User> getUser(int age);
    List<UserPosition> getVicinity(BigDecimal minlng, BigDecimal maxlng, BigDecimal minlat, BigDecimal maxlat);
    List<UserPosition> getvicinitysort(BigDecimal longitude,BigDecimal latitude);
    List<UserDetails> getUserDetails();
    UserAccount getUserByAccount(String account);

}