package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.mapper.DataMapper;
import com.mtx.metro.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Map<String,String>> implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    public List<Map<String,String>> selectAllTrueData() {
        return dataMapper.selectAllTrueData();
    }

    @Override
    public List<Map<String,String>> TrueDataAtTimeStation(String dateTime,String GTFSid) {
        return dataMapper.TrueDataAtTimeStation(dateTime,GTFSid);
    }

    @Override
    public Map<String,String> getTimeRange() {
        return dataMapper.getTimeRange();
    }

    @Override
    public Map<String,String> getThresholdInfo(String GTFSid) {
        return dataMapper.getThresholdInfo(GTFSid);
    }

    @Override
    public List<Map<String, String>> getWarningInfo(String dateTime) {
        return dataMapper.getWarningInfo(dateTime);
    }

    @Override
    public List<Map<String,String>> PredictDataAtStation(String GTFSid) {
        return dataMapper.PredictDataAtStation(GTFSid);
    }


}
