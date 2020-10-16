package com.bihell.dao;

import com.bihell.pojo.UserDetails;

import java.util.List;

public interface UserDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDetails record);

    int insertSelective(UserDetails record);

    UserDetails selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDetails record);

    int updateByPrimaryKey(UserDetails record);

    List<UserDetails> getUserDetails();

    UserDetails getUserDetailsByUid(Integer uid);
}