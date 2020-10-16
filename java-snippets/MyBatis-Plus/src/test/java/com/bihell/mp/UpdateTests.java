package com.bihell.mp;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
public class UpdateTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void updateById(){
        User user = new User();
        user.setId(1087982257332887553L);
        user.setAge(26);

        int rows = userMapper.updateById(user);
        System.out.println("影响记录数："+rows);
    }

    @Test
    public void updateByWrapper(){
        // wrapper 条件会出现在 where 中
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","李艺伟").eq("age",28);
        User user = new User();
        user.setId(1087982257332887553L);
        user.setAge(26);

        int rows = userMapper.update(user,updateWrapper);
        System.out.println("影响记录数："+rows);
    }

    /**
     * 传入实体 默认空的会在 where 条件里面
     */
    @Test
    public void updateByWrapper1(){
        User whereUser = new User();
        whereUser.setName("李艺伟");

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","李艺伟").eq("age",28);

        int rows = userMapper.update(whereUser,updateWrapper);
        System.out.println("影响记录数："+rows);
    }


    /**
     * 不传入实体在 wraper 里面修改值
     */
    @Test
    public void updateByWrapper2(){

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("name","李艺伟").eq("age",28).set("age",30);

        int rows = userMapper.update(null,updateWrapper);
        System.out.println("影响记录数："+rows);
    }

    @Test
    public void updateByLambda(){

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getName,"李艺伟").eq(User::getAge,28).set(User::getAge,30);

        int rows = userMapper.update(null,updateWrapper);
        System.out.println("影响记录数："+rows);
    }

    /**
     * 链式调用
     */
    @Test
    public void updateByWrapperLambdaChain(){
        boolean update = new LambdaUpdateChainWrapper<User>(userMapper)
                .eq(User::getName,"李艺伟").eq(User::getAge,30).set(User::getAge,31).update();

        System.out.println(update);
    }
}
