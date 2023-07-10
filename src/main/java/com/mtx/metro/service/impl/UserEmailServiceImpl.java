package com.mtx.metro.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.domain.User;
import com.mtx.metro.mapper.UserMapper;
import com.mtx.metro.service.UserEmailService;
import com.mtx.metro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserEmailServiceImpl extends ServiceImpl<UserMapper, User> implements UserEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    public boolean checkEmailCode(String email,String token,String code) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String mailCode = email + code;
        return passwordEncoder.matches(mailCode, token);
    }

    @Override
    @Transactional
    public String sendEmailCode(String email) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(email);
        msg.setSentDate(new Date());
        msg.setSubject("【地铁客流量预测系统】登录邮箱验证");
        String code = RandomUtil.randomNumbers(6);
        msg.setText("您本次登录的验证码为：" + code + "，有效期5分钟，请妥善保管，切勿泄露。");
        javaMailSender.send(msg);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String mailCode = email + code;
        return passwordEncoder.encode(mailCode);
    }
}
