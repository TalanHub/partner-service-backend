package com.zjl.partnerservice.service;

import com.zjl.partnerservice.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
 * @author zhenglao
 * @description 针对表【user(用户表)】的数据库操作Service
 * @createDate 2025-08-10 19:05:36
 */
public interface UserService extends IService<User> {


    /**
     * 用户注册
     *
     * @param userAccount   用户账户
     * @param userPassword  用户密码
     * @param checkPassword 重复密码
     * @return 用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword);


    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @return 用户id
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);


    /**
     * 用户注销（移除登录态）
     *
     * @param request
     * @return
     */
    void userLogout(HttpServletRequest request); // TODO: 代码可优化


    /**
     * 根据标签搜索用户
     *
     * @param tagNameList 标签名的列表
     * @return 符合要求的用户
     */
    List<User> searchUsersByTagNames(List<String> tagNameList);

//    List<User> searchUsersByTagNamesBySQL(List<String> tagNameList);

    /**
     * 用户脱敏
     *
     * @param originalUser 携带用户信息的用户对象
     * @return 信息脱敏后的用户对象
     */
    static User getMaskedUser(User originalUser) {
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
        maskedUser.setTags(originalUser.getTags()); // 新添加的标签字段需要被脱敏
        return maskedUser;
    }


}
