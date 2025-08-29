package com.zjl.partnerservice.common;

import com.zjl.partnerservice.model.domain.User;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
class BaseResponseTest {

    @Resource
    BaseResponse<User> obj;


    @Test
    public void testConstructor() {
        User user = new User();
        BaseResponse<User> userBaseResponse = new BaseResponse<>(4004, "i am message", user);
        List<BaseResponse<User>> responses = new ArrayList<>();


        BaseResponse t = new BaseResponse(444, "a", 32);



    }

}