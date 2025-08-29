package com.zjl.partnerservice;

//import org.junit.Test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class PartnerServiceApplicationTests {




    @Test
    void contextLoads() {

    }




    @Test
    void testBasicJavaSyntax() {
//        String s1 = "012345";
//        System.out.println(s1.length());
//
        String s1 = "hello";
        String s2 = "hello";
        String s3 = "YES";
        System.out.println(s1.equals(s2) + " " + s1.equals(s3));



    }

    @Test
    void testCharacter() {
        String str = "lalala434343";
        // 字符串中不能包含除字母数字以外的字符
        String invalidPattern = "[^a-zA-Z0-9]";
        Matcher matcher = Pattern.compile(invalidPattern).matcher(str);
        if (matcher.find()) {
            System.out.println("错了，包含特殊字符了");
        } else {
            System.out.println("没错，干得好");
        }

    }

}
