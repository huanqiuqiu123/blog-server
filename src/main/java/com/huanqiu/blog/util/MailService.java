package com.huanqiu.blog.util;

import jakarta.annotation.Resource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;


/**
 * @author huanqiu
 * @version 1.0
 * @description
 * @date 2023/02/18 下午 4:05
 */
@Slf4j
@Component
public class MailService {
    @Value("${spring.mail.username}")
    private String username;

    @Resource
    private JavaMailSender javaMailSender;

    /**
     * 发送文本邮件
     *
     * @param to      收件人
     * @param subject 发送主题
     * @param content 发送内容
     */
    public void sendTextMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送HTML邮件
     *
     * @param to      收件人
     * @param subject 发送主题
     * @param content 发送内容
     */
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(username);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
