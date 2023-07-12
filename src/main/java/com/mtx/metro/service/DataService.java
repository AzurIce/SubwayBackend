package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface DataService extends IService<Map<String,String>> {

    List<Map<String,String>> TrueDataAtTime(String dateTime);

    List<Map<String,String>> TrueDataAtTimeStation(String dateTime,String GTFSid);

    List<Map<String,String>> PredictDataAtStation(String GTFSid);

    Map<String,String> getTimeRange();

    Map<String,String> getThresholdInfo(String GTFSid);

    List<Map<String, String>> getWarningInfo(String dateTime);

}
