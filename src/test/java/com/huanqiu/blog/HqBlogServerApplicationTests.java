package com.huanqiu.blog;

import com.auth0.jwt.interfaces.Claim;
import com.huanqiu.blog.mapper.UserMapper;
import com.huanqiu.blog.service.UserService;
import com.huanqiu.blog.util.JwtUtil;
import com.huanqiu.blog.util.MailService;
import com.huanqiu.blog.util.SnowFlakeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class HqBlogServerApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {

        for (int i = 0; i < 20; i++) {
            System.out.println(SnowFlakeUtil.getNextId());
        }
    }

    @Test
    public void test() {
        String s = DigestUtils.md5DigestAsHex("huanqiu".getBytes());
        System.out.println(s);
    }

    @Test
    public void test01() {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("id", "123456789");
            String token = JwtUtil.getToken(map, 1);
            System.out.println(token);
            Map<String, Claim> verify = JwtUtil.verify(token);
            String id = verify.get("id").asString();
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private MailService mailService;

    @Test
    public void test02() {
        mailService.sendTextMail("2277519822@qq.com", "sabie", "sabie");
    }

    @Test
    public void testInsertUser() {
        System.out.println(userService.addUser(SnowFlakeUtil.getNextId(), "123456", "2364521714@qq.com"));
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectUser() {
        System.out.println(userMapper.selectUserByAccount("2364521714@qq.com"));
    }


}
