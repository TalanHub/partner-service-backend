package com.zjl.partnerservice.service;

import com.zjl.partnerservice.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author zhenglao
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-08-10 19:05:36
*/
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 重复密码
     * @return 用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @return 用户id
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户注销（移除登录态）
     * @param request
     * @return
     */
    void userLogout(HttpServletRequest request); // TODO: 代码可优化








    /**
     * 用户脱敏
     *
     * @param originalUser 携带用户信息的用户对象
     * @return 信息脱敏后的用户对象
     */
    User getMaskedUser(User originalUser);






}
