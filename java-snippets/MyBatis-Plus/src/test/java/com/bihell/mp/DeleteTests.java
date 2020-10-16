package com.bihell.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.bihell.mp.entity.User;
import com.bihell.mp.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void deleteById(){

        int rows = userMapper.deleteById(1087982257332887553L);
        System.out.println("影响记录数："+rows);
    }

    @Test
    public void deleteByWarpper(){
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.lambdaQuery();
        lambdaQueryWrapper.eq(User::getAge,27).or().gt(User::getAge,41);
        int rows = userMapper.delete(lambdaQueryWrapper);
        System.out.println("删除条数："+rows);
    }
}
