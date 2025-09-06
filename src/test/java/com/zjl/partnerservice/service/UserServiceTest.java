package com.zjl.partnerservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.partnerservice.model.domain.User;
import jakarta.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * 用户服务测试
 *
 * @author zhengjialong
 */
@SpringBootTest
public class UserServiceTest {


    @Resource
    private UserService userService;

    @Test
    public void testAddUser() {
        User user = new User();

        user.setUsername("zj");
        user.setAge(24);
        user.setGender(0);
        user.setUserPassword("xxx");
        user.setEmail("dashabi@qq.com");


        boolean result = userService.save(user);
        System.out.println(user.getId());
        Assertions.assertTrue(result);
        System.out.println("DONE...............................................");

    }

    @Test
    void userRegister() {
        String userAccount = "zjl009";
        String userPassword = "12345678";
        String checkPassword = "";
        // 重复密码为空
        long resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, resultId);

        // 两次密码不一样
        checkPassword = "12345678000";
        resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, resultId);

        // 账户包含特殊字符
        checkPassword = "12345678";
        userAccount = "zjl zjl"; // 特殊字符
        resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, resultId);

        // 账户太短
        userAccount = "zz";
        resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, resultId);

        // 账户不能重复
        userAccount = "909090";
        resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, resultId);

        //没错了
        userAccount = "zjl010";
        resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertTrue(resultId > 0);
        System.out.println("插入的新用户id为" + resultId);
    }





    @Test
    public void testQueryWrapper() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", "zjzzzzl");
        User user = userService.getOne(queryWrapper);

        System.out.println(user);

        userService.removeById(user.getId());
    }

    @Test
    public void testDelete() {
        boolean result = userService.removeById(-10);
        System.out.println("removeById(-10)'s result is " + result);
        result = userService.removeById(10);
        System.out.println("removeById(-10)'s result is " + result);


    }


    @Test
    void searchUsersByTags() {
        System.out.println("测试方法开始运行：");
        List<String> tagNamelist = Arrays.asList("游戏", "科技");
        List<User> resultUsers = userService.searchUsersByTagNames(tagNamelist);
        System.out.println("测试结果如下：");
        System.out.println("符合要求的数量为：" + resultUsers.size() + "个");
        System.out.println(resultUsers);
    }

//    @Test
//    void searchUsersByTagNamesBySQL() {
//        List<String> tagNamelist = Arrays.asList("游戏", "科技");
//        List<User> resultUsers = userService.searchUsersByTagNamesBySQL(tagNamelist);
//        System.out.println("测试结果如下：");
//        System.out.println("符合要求的数量为：" + resultUsers.size() + "个");
//        System.out.println(resultUsers);
//
//
//    }
}