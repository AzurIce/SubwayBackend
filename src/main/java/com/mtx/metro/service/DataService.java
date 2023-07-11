package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface DataService extends IService<Map<String,String>> {

    List<Map<String,String>> selectAllTrueData();

    List<Map<String,String>> selectAtATime(String dateTime);

    List<Map<String,String>> selectAllPredictData();

    Map<String,String> getTimeRange();

    Map<String,String> getStationInfo(String GTFSid);

}
