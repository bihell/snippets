package com.bihell.mp;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.bihell.mp.entity.User;
import com.bihell.mp.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MpTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void select() {
        List<User> list = userMapper.selectList(null);
        Assert.assertEquals(5, list.size());
        list.forEach(System.out::println);
    }

    /**
     * 插入
     */
    @Test
    public void insert() {
        User user = new User();
        user.setName("刘明强");
        user.setAge(31);
        user.setManagerId(1087982257332887553L);
        user.setCreateTime(LocalDateTime.now());
        int rows = userMapper.insert(user);
        System.out.println("影响记录数：" + rows);
    }

    @Test
    public void selectById() {
        User user = userMapper.selectById(1152812528417214466L);
        System.out.println(user);
    }

    @Test
    public void selectIds() {
        List<Long> idsList = Arrays.asList(1094592041087729666L, 1088248166370832385L);
        List<User> userList = userMapper.selectBatchIds(idsList);
        userList.forEach(System.out::println);
    }

    @Test
    public void selectByMap() {
        //map.put("name","王天风")
        //map.put("age",30)
        //where name="王天风" and age=30
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("name", "王天风");
        columnMap.put("age", 25);
        List<User> userList = userMapper.selectByMap(columnMap);
        userList.forEach(System.out::println);
    }

    /**
     * 条件构造器查询
     */
    /**
     * 名字中包含雨并且年龄小于40
     * name like '%雨%' and age<40
     */
    @Test
    public void selectByWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 另一种定义方式
        // QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字中包含雨并且年龄大于等于20且小于等于40且 email 不为空
     * name like '%雨%' and age between 20 and 40 and email is not null
     */
    @Test
    public void selectByWrapper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者年亮大于等于25，按照年龄降序排列，年龄相同按照 id 升序排列
     * name like '王%' or age>=25 order by age desc,id asc
     */
    @Test
    public void selectByWrapper3() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or().ge("age", 25).orderByDesc("age").orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 创建日期为2019年2月14日并且直属上级为名字王姓
     * date_format(create_time,'%Y-%m-%d') and manager_id in (select id from user where name like '王%')
     */
    @Test
    public void selectByWrapper4() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14").inSql("manager_id", "select id from user where name like '王%'");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王并且（棉铃小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectByWrapper5() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").and(wq -> wq.lt("age", 40).or().isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 名字为王姓或者 （年龄小于40并且棉铃大于20 并且邮箱不为空）
     * name like '王%' or (age<40 and age>20 and email is not null)
     */
    @Test
    public void selectByWrapper6() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("name", "王").or(wq -> wq.lt("age", 40).gt("age", 20).isNotNull("email"));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄小于40或邮箱不为空并且名字为王姓
     * (age <40 or email is not null) and name like '王%'
     */
    @Test
    public void selectByWrapper7() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wa -> wa.lt("age", 40).or().isNotNull("email")).likeRight("name", "王");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 年龄为30、31、34、35
     * age in (30,31,34,35)
     */
    @Test
    public void selectByWrapper9() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35));
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回满足条件的其中一条语句
     */
    @Test
    public void selectByWrapper10() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("age", Arrays.asList(30, 31, 34, 35)).last("limit 1");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * select 不返回全部列
     */
    @Test
    public void selectByWrapperSupper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 另一种定义方式
        // QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.select("id", "name").like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * select 不返回某列
     */
    @Test
    public void selectByWrapperSupper2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 另一种定义方式
        // QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.select(User.class, info -> !info.getColumn().equals("create_time") && !info.getColumn().equals("manager_id")).like("name", "雨").lt("age", 40);
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    private void condition(String name, String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        if (StringUtils.isNotEmpty(name)) {
//            queryWrapper.like("name", name);
//        }
//        if (StringUtils.isNotEmpty(email)) {
//            queryWrapper.like("email", name);
//        }

        queryWrapper.like(StringUtils.isNotEmpty(name),"name",name).like(StringUtils.isNotEmpty(email),"email",email);

    }

    /**
     * condition 条件,控制字段是否加入到语句中
     */
    @Test
    public void testCondition() {
        String name = "王";
        String email = "";
        condition(name, email);
    }

    /**
     * 以实体传入
     */
    @Test
    public void selectByWrapperEntity(){
        User whereUser = new User();
        whereUser.setName("刘红雨");
        whereUser.setAge(32);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * allEq
     */

    @Test
    public void selectByWrapperAllEq(){

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        Map<String,Object> params = new HashMap<>();
        params.put("name","王天风");

        // 默认情况下如果查询字段有 null 的话插叙语句会编程 is null
        params.put("ange",null);


        queryWrapper.allEq(params);

        //值为 null 的会忽略掉
        queryWrapper.allEq(params,false);

        //条件过滤，过滤掉不等于 name
        queryWrapper.allEq((k,v)->!k.equals("name"),params);

        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 返回 map
     */

    @Test
    public void selectByWrapperMaps() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 另一种定义方式
        // QueryWrapper<User> query = Wrappers.<User>query();
        queryWrapper.like("name", "雨").lt("age", 40);
        List<Map<String,Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 统计查询
     * 按照直属上级分组，查询每组的平均年龄、最大年龄、最小年龄。并且只取年龄综合小于500的组。
     * select avg(age) avg_age,min(age) min_age,max(age) max_age
     * form user
     * group by manager_id
     * having sum(age)< 500
     */

    @Test
    public void selectByWrapperMaps2() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}",500);
        List<Map<String,Object>> userList = userMapper.selectMaps(queryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * 只返回一列
     */
    @Test
    public void selectByWrapperObjs() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("avg(age) avg_age","min(age) min_age","max(age) max_age")
                .groupBy("manager_id").having("sum(age)<{0}",500);
        List<Object> userList = userMapper.selectObjs(queryWrapper);
        userList.forEach(System.out::println);
    }


    /**
     * 查记录数
     */
    @Test
    public void selectByWrapperCount() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);

        Integer count = userMapper.selectCount(queryWrapper);
        System.out.println("总记录数:"+count);
    }

    /**
     * 只返回一条记录
     */

    @Test
    public void selectByWrapperOne() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);

        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * lambda 构造器
     */

    @Test
    public void selectLambda() {
        // 第一种方式
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();

        // 第二种方式
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 第三种方式
        LambdaQueryWrapper<User> lambdaQuery = Wrappers.lambdaQuery();

        lambdaQuery.like(User::getName,"雨").lt(User::getAge,40);
        List<User> userList = userMapper.selectList(lambdaQuery);

        userList.forEach(System.out::println);

    }

    /**
     * 名字为王并且（棉铃小于40或邮箱不为空）
     * name like '王%' and (age<40 or email is not null)
     */
    @Test
    public void selectLambda2() {
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();

        lambdaQuery.likeRight(User::getName,"王").and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<User> userList = userMapper.selectList(lambdaQuery);
        userList.forEach(System.out::println);
    }


    @Test
    public void selectLambda3() {
        List<User> userList = new LambdaQueryChainWrapper<User>(userMapper)
                .like(User::getName,"雨").ge(User::getAge,20).list();
        userList.forEach(System.out::println);

    }


    /**
     * 自定义方法 1  SQL 写在了接口中
     */
    @Test
    public void selectMy() {
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();

        lambdaQuery.likeRight(User::getName,"王")
                .and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<User> userList = userMapper.selectAll(lambdaQuery);
        userList.forEach(System.out::println);
    }


    /**
     * 自定义方法 2  写在 XML 中
     */
    @Test
    public void selectXML() {
        LambdaQueryWrapper<User> lambdaQuery = new LambdaQueryWrapper<>();

        lambdaQuery.likeRight(User::getName,"王")
                .and(lqw->lqw.lt(User::getAge,40).or().isNotNull(User::getEmail));
        List<User> userList = userMapper.selectAllXml(lambdaQuery);
        userList.forEach(System.out::println);
    }

    /**
     * 分页 返回 list
     */
    @Test
    public void selectPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",26);

        Page<User> page = new Page<User>(1,2);

        IPage<User> iPage = userMapper.selectPage(page,queryWrapper);
        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);

    }

    /**
     * 分页 返回 map
     */
    @Test
    public void selectPage2(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",26);

        Page<User> page = new Page<>(1, 2);

        IPage<Map<String,Object>> iPage = userMapper.selectMapsPage(page,queryWrapper);

        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);

    }

    /**
     * 分页 不进行 count
     */
    @Test
    public void selectPage3(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",26);

        Page<User> page = new Page<>(1, 2, false);

        IPage<Map<String,Object>> iPage = userMapper.selectMapsPage(page,queryWrapper);

        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<Map<String, Object>> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

    /**
     * 分页 自定义 sql
     */
    @Test
    public void selectMyPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",26);

        Page<User> page = new Page<>(1, 2);

        IPage<User> iPage = userMapper.selectUserPage(page,queryWrapper);

        System.out.println("总页数"+iPage.getPages());
        System.out.println("总记录数"+iPage.getTotal());
        List<User> userList = iPage.getRecords();
        userList.forEach(System.out::println);
    }

}
