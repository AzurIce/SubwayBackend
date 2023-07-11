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
    public List<Map<String,String>> selectAtATime(String dateTime) {
        return dataMapper.selectAtATime(dateTime);
    }

    @Override
    public Map<String,String> getTimeRange() {
        return dataMapper.getTimeRange();
    }

    @Override
    public Map<String,String> getStationInfo(String GTFSid) {
        return dataMapper.getStationInfo(GTFSid);
    }

    @Override
    public List<Map<String,String>> selectAllPredictData() {
        return dataMapper.selectAllPredictData();
    }


}
