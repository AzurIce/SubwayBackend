package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mtx.metro.domain.PredictData;
import com.mtx.metro.domain.TrueData;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PredictMapper extends BaseMapper<PredictData> {
    List<PredictData> selectAll();
}
