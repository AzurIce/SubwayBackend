package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.domain.TrueData;
import com.mtx.metro.mapper.TrueMapper;
import com.mtx.metro.service.TrueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TrueServiceImpl extends ServiceImpl<TrueMapper, TrueData> implements TrueService {

    @Autowired
    private TrueMapper trueMapper;

    @Override
    public List<TrueData> selectAll() {
        return trueMapper.selectAll();
    }

    @Override
    public List<TrueData> selectAtATime(String dateTime) {
        return trueMapper.selectAtATime(dateTime);
    }
}
