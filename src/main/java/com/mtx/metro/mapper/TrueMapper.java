package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtx.metro.domain.TrueData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Mapper
@Repository
@Component
public interface TrueMapper extends BaseMapper<TrueData> {

    List<TrueData> selectAll();

    List<TrueData> selectAtATime(@Param("dateTime") String dateTime);
}
