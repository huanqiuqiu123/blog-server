package com.huanqiu.blog.controller;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.huanqiu.blog.common.ResponseResult;
import com.huanqiu.blog.domain.dto.LoginUserDto;
import com.huanqiu.blog.domain.dto.RegisterUserDto;
import com.huanqiu.blog.domain.pojo.SelectUserByAccountPo;
import com.huanqiu.blog.service.UserService;
import com.huanqiu.blog.util.DigestUtil;
import com.huanqiu.blog.util.JwtUtil;
import com.huanqiu.blog.util.MailService;
import com.huanqiu.blog.util.SnowFlakeUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2022/12/31 下午 2:39
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private MailService mailService;

    @PostMapping("/send_code")
    public ResponseResult sendCode(@RequestBody Map<String,String> params) {
        String email = params.get("email");
        log.info(email);
        if (StringUtils.hasText(email)) {
            //验证邮箱格式
            String emailRegex = "^\\w+@qq\\.com$";
            if (!email.matches(emailRegex)) {
                return new ResponseResult(601, "邮箱格式不正确", null);
            }
            Integer count = userService.selectUserByEmail(email);
            if (count >= 1) {
                return new ResponseResult(602, "邮箱已被注册", null);
            } else {
                //用户未注册则发送验证码
                Random random = new Random();
                int code = 10000 + random.nextInt(89999);
                mailService.sendHtmlMail(email, "验证码", "验证码为：<strong>" + code + "</strong>");
                Map<String, String> tokenMap = new HashMap<>();
                tokenMap.put("email", email);
                tokenMap.put("code", String.valueOf(code));
                //5分钟过期
                String token = JwtUtil.getToken(tokenMap, 5 * 60);
                Map<String, String> resultMap = new HashMap<>();
                resultMap.put("token", token);
                return new ResponseResult(200, "验证码发送成功", resultMap);
            }
        } else {
            return new ResponseResult(603, "邮箱不能为空", null);
        }
    }

    @PostMapping("/register")
    public ResponseResult register(@RequestBody RegisterUserDto registerUserDto, @RequestHeader String token) {
        if (StringUtils.hasText(token)) {
            try {
                Map<String, Claim> verify = JwtUtil.verify(token);
                String emailInToken = verify.get("email").asString();
                String codeInToken = verify.get("code").asString();
                String email = registerUserDto.getEmail();
                String code = registerUserDto.getCode();
                if (StringUtils.hasText(email) && StringUtils.hasText(code)) {
                    if (email.equals(emailInToken) && code.equals(codeInToken)) {
                        //验证通过
                        String password = registerUserDto.getPassword();
                        if (StringUtils.hasText(password)) {
                            //密码加密
                            String md5Password = DigestUtil.encrypt(password);
                            //生成用户id
                            long userId = SnowFlakeUtil.getNextId();
                            int count = userService.addUser(userId, md5Password, email);
                            return new ResponseResult(count == 1 ? 200 : 601, count == 1 ? "注册成功" : "注册失败", null);
                        } else {
                            return new ResponseResult(602, "密码不能为空", null);
                        }
                    } else {
                        return new ResponseResult(603, "验证码错误", null);
                    }
                } else {
                    return new ResponseResult(604, "邮箱或验证码不能为空", null);
                }
            } catch (TokenExpiredException e) {
                e.printStackTrace();
                return new ResponseResult(605, "验证码已过期", null);
            }
        } else {
            return new ResponseResult(605, "token不能为空", null);
        }
    }

    @PostMapping("/login")
    public ResponseResult login(@RequestBody LoginUserDto loginUserDto) {
        String account = loginUserDto.getAccount();
        if (StringUtils.hasText(account)) {
            String password = loginUserDto.getPassword();
            if (StringUtils.hasText(password)) {
                SelectUserByAccountPo selectUserByAccountPo = userService.selectUserByAccount(account);
                if (selectUserByAccountPo == null) {
                    return new ResponseResult(604, "该用户未注册", null);
                }
                if (DigestUtil.encrypt(password).equals(selectUserByAccountPo.getPassword())) {
                    //账号密码正确 生成token返回
                    Map<String, String> tokenMap = new HashMap<>();
                    tokenMap.put("userId", String.valueOf(selectUserByAccountPo.getId()));
                    String returnToken = JwtUtil.getToken(tokenMap);
                    Map<String, String> resultMap = new HashMap<>();
                    resultMap.put("token", returnToken);
                    return new ResponseResult(200, "登录成功", resultMap);
                } else {
                    return new ResponseResult(603, "密码错误", null);
                }
            } else {
                return new ResponseResult(602, "密码不能为空", null);
            }
        } else {
            return new ResponseResult(601, "用户名或邮箱不能为空", null);
        }
    }
}
