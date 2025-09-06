package com.zjl.partnerservice.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.partnerservice.model.domain.User;
import com.zjl.partnerservice.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Resource
    private UserService userService;


    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("Charlie Chaplin");
        user.setUserAccount("2333333");
        userMapper.insert(user);
    }


    /**
     * 测试逻辑删除是否配置成功
     */
    @Test
    public void testLogicDelete() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "deletedUser");
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            System.out.println("Success! The user has been logically deleted.");
        } else {
            System.out.println("It failed. The user hasn't been deleted.");
        }
        System.out.println("Print user:\n" + user);
    }


    @Test
    public void testUserQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", "zjl002");
        List<User> userList = userService.list(queryWrapper);
        Assertions.assertNotNull(userList);
        System.out.println(userList);
        /*
        之前查不到的原因：数据库中用户名确实没有符合查询条件的，
        我犯傻把账户当用户名啦
         */
    }

}
