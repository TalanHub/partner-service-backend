package com.zjl.partnerservice.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 用来测试session，很快会删掉
 */
@RestController
public class TempController {

    @GetMapping("/set")
    public String funcSet(HttpServletRequest request) {
        request.getSession().setAttribute("UserId", "001");
        return "设置session成功";
    }


    @GetMapping("/get")
    public Object funcGet(HttpServletRequest request) {
        return request.getSession().getAttribute("UserId");
    }
}
