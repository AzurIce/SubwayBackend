package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.exception.ServiceException;
import com.mtx.metro.mapper.DataMapper;
import com.mtx.metro.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class DataServiceImpl extends ServiceImpl<DataMapper, Map<String,String>> implements DataService {

    @Autowired
    private DataMapper dataMapper;

    @Override
    @Transactional
    public List<Map<String,String>> getAllStationName() {
        List<Map<String,String>> list = dataMapper.getAllStationName();
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    @Transactional
    public List<Map<String,String>> TrueDataAtTime(String dateTime) {
        List<Map<String,String>> list = dataMapper.TrueDataAtTime(dateTime);
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(), "记录不存在");
    }

    @Override
    @Transactional
    public List<Map<String,String>> TrueDataAtTimeStation(String dateTime,String GTFSid) {
        List<Map<String,String>> list = dataMapper.TrueDataAtTimeStation(dateTime,GTFSid);
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    @Transactional
    public Map<String,String> getTimeRange() {
        Map<String,String> map = dataMapper.getTimeRange();
        if(!map.isEmpty()) return map;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    @Transactional
    public Map<String,String> getThresholdInfo(String GTFSid) {
        Map<String,String> map = dataMapper.getThresholdInfo(GTFSid);
        if(!map.isEmpty()) return map;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    @Transactional
    public List<Map<String, String>> getWarningInfo(String dateTime) {
        List<Map<String,String>> list = dataMapper.getWarningInfo(dateTime);
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    public List<Map<String, String>> getOverloadInfo(String dateTime) {
        List<Map<String,String>> list = dataMapper.getOverloadInfo(dateTime);
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }

    @Override
    @Transactional
    public List<Map<String,String>> PredictDataAtStation(String GTFSid) {
        List<Map<String,String>> list = dataMapper.PredictDataAtStation(GTFSid);
        if(!list.isEmpty()) return list;
        else throw new ServiceException(HttpStatus.NOT_FOUND.toString(),"记录不存在");
    }


}
