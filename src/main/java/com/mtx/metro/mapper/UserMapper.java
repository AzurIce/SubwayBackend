package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mtx.metro.domain.User;
import com.mtx.metro.enums.PerEnum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
@Component
public interface UserMapper extends BaseMapper<User> {

    //查询权限
    PerEnum selectPermsByUserId(@Param("uid") String uid);

    //查询全部用户
    List<User> selectAllUserInfo();

    //按照用户id查询用户
    User selectUserByID(@Param("uid") String uid);
}
