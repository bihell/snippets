package com.bihell.service;

import com.bihell.pojo.UserDetails;

public interface CacheService {
    UserDetails getUserDetailsByUid(int uid);

    UserDetails updateUserInfo(UserDetails userDetails);

    int delUserInfoById(int uid);
}
