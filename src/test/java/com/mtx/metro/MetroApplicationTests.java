package com.mtx.metro;

import com.mtx.metro.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MetroApplicationTests {

//    @Autowired
//    private UserMapper userMapper;

//    @Test
//    public void testBCrypt(){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode1 = passwordEncoder.encode("12345");
//        String encode2 = passwordEncoder.encode("12345");
//        String encode3 = passwordEncoder.encode("12345");
//        String encode4 = passwordEncoder.encode("12345");
//        String encode5 = passwordEncoder.encode("12345");
//        System.out.println(encode1);
//        System.out.println(encode2);
//        System.out.println(encode3);
//        System.out.println(encode4);
//        System.out.println(encode5);
//        System.out.println(passwordEncoder.matches("12345",
//                "$2a$10$5YcsuMVQx/W1s6nIy4SmL.hltz8MIIQGHxKpFCoqDlXXujWt3m9OS"));
//    }

    /*
        $2a$10$5YcsuMVQx/W1s6nIy4SmL.hltz8MIIQGHxKpFCoqDlXXujWt3m9OS
        $2a$10$PuCoxBWh3fDPFCCzRvuP9u9I2U5uYWxj1YxCmwmzHxggzGc1xT/oK
        $2a$10$3bk4wqejuc7Uf4oCxLL54.vz.rlqHyRNtYfoyhKai3iJt4eLddgza
        $2a$10$RdBoDPasCt/2oXoZurtOruLYXnTyhC1NN9qgAQA8RoZZ74FbAZtZ.
         */
}
