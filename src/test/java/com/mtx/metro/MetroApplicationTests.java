package com.mtx.metro;

import com.mtx.metro.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class MetroApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testBCrypt(){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        String encode1 = passwordEncoder.encode("admin");
//        String encode2 = passwordEncoder.encode("gov");
//        String encode3 = passwordEncoder.encode("cjx");
//        String encode4 = passwordEncoder.encode("xyh");
//        System.out.println(encode1);
//        System.out.println(encode2);
//        System.out.println(encode3);
//        System.out.println(encode4);
//        System.out.println(passwordEncoder.matches("12345",
//                "$2a$10$5YcsuMVQx/W1s6nIy4SmL.hltz8MIIQGHxKpFCoqDlXXujWt3m9OS"));
    }
    /*
$2a$10$nKJlZPkK8GdvdBuxJAYTxe0g.QSlERfOGZcZ9A6tn0V4eb5oTgOqu
$2a$10$./zvYuG8.8Pl/V3.xxhLQuE84LF0OO6eQf1r8X7mA8O2ccPCXSnOe
$2a$10$v5CUzfonP6zauM.pfWRekOjriZvnE.Vj3qVMw9ph9hEvIl7VYslIi
$2a$10$VnVYwJPG2Vu2vVORLNzoxuJAqI9W/gwZx9NUU5eLPR8sc3h.8EhU.
         */
}
