package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtx.metro.domain.TrueData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface TrueMapper extends BaseMapper<TrueData> {

    List<TrueData> selectAll();

    List<TrueData> selectAtATime(@Param("dateTime") Date dateTime);
}
