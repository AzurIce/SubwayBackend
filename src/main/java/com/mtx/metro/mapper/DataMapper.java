package com.mtx.metro.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
@Component
public interface DataMapper extends BaseMapper<Map<String,String>> {

    //查询所有真实数据
    List<Map<String,String>> selectAllTrueData();

    //根据日期时间查询真实数据
    List<Map<String,String>> selectAtATime(@Param("dateTime") String dateTime);

    //查询预测数据
    List<Map<String,String>> selectAllPredictData();

    //查询时间范围
    Map<String,String> getTimeRange();

    //查询站点数据：站名，经纬度，具体客流量，预测客流量
    Map<String,String> getStationInfo(@Param("GTFSid") String GTFSid);
}
