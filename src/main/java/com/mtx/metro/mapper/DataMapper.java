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

    //查询所有站点名称
    List<Map<String,String>> getAllStationName();

    //查询所有真实数据
    List<Map<String,String>> TrueDataAtTime(@Param("dateTime") String dateTime);

    //根据日期时间查询真实数据
    List<Map<String,String>> TrueDataAtTimeStation(@Param("dateTime") String dateTime,@Param("GTFSid") String GTFSid);

    //查询预测数据
    List<Map<String,String>> PredictDataAtStation(@Param("GTFSid") String GTFSid);

    //查询时间范围
    Map<String,String> getTimeRange();

    //查询某站点avg、max客流数据
    Map<String,String> getThresholdInfo(@Param("GTFSid") String GTFSid);

    //查询某日期时间的流量预警信息
    List<Map<String,String>> getWarningInfo(@Param("dateTime") String dateTime);

    //查询某时间所有过载站点信息
    List<Map<String,String>> getOverloadInfo(@Param("dateTime") String dateTime);
}
