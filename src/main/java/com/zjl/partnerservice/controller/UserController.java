package com.zjl.partnerservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zjl.partnerservice.Constant.UserConstant;
import com.zjl.partnerservice.common.BaseResponse;
import com.zjl.partnerservice.common.ErrorCode;
import com.zjl.partnerservice.common.StdResponse;
import com.zjl.partnerservice.model.domain.request.UserLoginRequest;
import com.zjl.partnerservice.model.domain.request.UserRegisterRequest;
import com.zjl.partnerservice.model.domain.User;

import com.zjl.partnerservice.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户接口
 *
 * @author zhenglao
 */

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private UserService userService;


    @PostMapping("/login")
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
//            throw new BussinessException(ErrorCode.PARAMS_NULL);
            // return null;
            return StdResponse.error(ErrorCode.PARAMS_ERROR,"请求参数为空");
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return StdResponse.success(user);
    }

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return StdResponse.error(ErrorCode.PARAMS_ERROR, "您输入的请求参数为空");
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return StdResponse.error(ErrorCode.PARAMS_ERROR, "您输入的请求参数为空");
        }
        long resultId = userService.userRegister(userAccount, userPassword, checkPassword);
        return StdResponse.success(resultId);
    }


    @GetMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
//            return StdResponse.fail("登陆失败");
            return StdResponse.error(ErrorCode.PARAMS_ERROR, "请求对象不能为空");
        }
        userService.userLogout(request);
        return StdResponse.success(true);
    }




    @GetMapping("/search/{username}")
    public BaseResponse<List<User>> searchByName(@PathVariable String username, HttpServletRequest request) {
        // 鉴权
        if (!isAdmin(request)) {
            return StdResponse.error(ErrorCode.NO_AUTH);
//            return StdResponse.fail("用户不是管理员，不许查询");
        }
        if (username == null) {
            return StdResponse.error(ErrorCode.PARAMS_ERROR, "用户名为空");
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("username", username);

        List<User> users = userService.list(queryWrapper);
        List<User> maskedUsers = users.stream().map(user -> userService.getMaskedUser(user)).collect(Collectors.toList());

        return StdResponse.success(maskedUsers);

    }



    @GetMapping("/delete")
    public BaseResponse<Boolean> deleteUserById(@RequestParam long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return StdResponse.error(ErrorCode.NO_AUTH,"用户不是管理员，不许删除");
        }
        if (id <= 0) {
            return StdResponse.error(ErrorCode.PARAMS_ERROR, "用户ID不能小于零。");
        }
        Boolean resultBoolean =  userService.removeById(id);
//        return new BaseResponse<>(500, "代码没写好，结果不确定，对不起", resultBoolean);
        if (resultBoolean) {
            return StdResponse.success(true);
        } else {
            return StdResponse.error(ErrorCode.NOT_FOUND, "用户不存在或已被删除。");
        }

    }

    // TODO: 这些方法都可被润色，复杂的逻辑应该写在service里



    private boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(UserConstant.USER_LOGIN_STATE);
        User user = (User)userObj;
        return user != null && user.getUserRole() == UserConstant.ADMIN_ROLE;
    }
}
