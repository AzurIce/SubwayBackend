package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
    PerEnum selectPermsByUserId(@Param("uid") Long uid);
}
