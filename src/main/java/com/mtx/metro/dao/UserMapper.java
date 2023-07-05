package com.mtx.metro.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtx.metro.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
@Component
public interface UserMapper extends BaseMapper<User> {

    //用户登陆
    Boolean userLogin(@Param("uid") String uid, @Param("pwd") String pwd);
}
