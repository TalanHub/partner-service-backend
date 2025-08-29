package com.zjl.partnerservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zjl.partnerservice.Constant.UserConstant;
import com.zjl.partnerservice.common.ErrorCode;
import com.zjl.partnerservice.exception.BussinessException;
import com.zjl.partnerservice.model.domain.User;
import com.zjl.partnerservice.service.UserService;
import com.zjl.partnerservice.mapper.UserMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* @author zhenglao
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-08-10 19:05:36
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{


    @Resource
    private UserMapper userMapper;

    /**
     * 盐值，混淆密码
     */
    private static final String SALT = "zjl";



    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {

        // 账户密码 不能为空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BussinessException(ErrorCode.PARAMS_ERROR, "请求参数为空");
//            return -1;
        }

        // 两次输入的密码不相同
        if (!userPassword.equals(checkPassword)) {
            throw new BussinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不同");
//            return -1;
        }

        // 账户能小于4位，密码不能小于8位
        if (userAccount.length() < 4 || userPassword.length() < 8) {
            return -1;
        }

        // 账户不能包含特殊字符
        String invalidPattern = "[^a-zA-Z0-9]"; // 不属于字母数字的字符
        Matcher matcher = Pattern.compile(invalidPattern).matcher(userAccount);
        if (matcher.find()) { // 包含特殊字符
            return -1;
        }

        // 账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);
        long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 2.加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptedPassword);

        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }

        return user.getId();
    }


    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        // 账户不能包含特殊字符
        String invalidPattern = "[^a-zA-Z0-9]"; // 不属于字母数字的字符
        Matcher matcher = Pattern.compile(invalidPattern).matcher(userAccount);
        if (matcher.find()) { // 包含特殊字符
            return null;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_account", userAccount);

        // 没有该用户
        long count = userMapper.selectCount(queryWrapper);
        if (count == 0) {
            log.info("user doesn't exist");
            return null;
        }

        // 密码先加密
        String encryptedPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        queryWrapper.eq("user_password", encryptedPassword);
        // 用户密码错误
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, user password is incorrect");
            return null;
        }

        // 用户信息脱敏
        User maskedUser = getMaskedUser(user);

        // TODO: 记录登录态
        request.getSession().setAttribute(UserConstant.USER_LOGIN_STATE, maskedUser);
        
        //登陆成功
        return maskedUser;
    }

    @Override
    public void userLogout(HttpServletRequest request) {
        request.getSession().removeAttribute(UserConstant.USER_LOGIN_STATE);
    }

    @Override
    public User getMaskedUser(User originalUser) {
        User maskedUser = new User();
        maskedUser.setId(originalUser.getId());
        maskedUser.setUsername(originalUser.getUsername());
        maskedUser.setAge(originalUser.getAge());
        maskedUser.setGender(originalUser.getGender());
        maskedUser.setBirthday(originalUser.getBirthday());
        maskedUser.setUserAccount(originalUser.getUserAccount());
        maskedUser.setUserPassword("");
        maskedUser.setEmail(originalUser.getEmail());
        maskedUser.setPhoneNumber(originalUser.getPhoneNumber());
        maskedUser.setAvatarUrl(originalUser.getAvatarUrl());
        maskedUser.setUserRole(originalUser.getUserRole());
        maskedUser.setUserStatus(originalUser.getUserStatus());
        maskedUser.setCreateTime(originalUser.getCreateTime());

        return maskedUser;
    }
}




