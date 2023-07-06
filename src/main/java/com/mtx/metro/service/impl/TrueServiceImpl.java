package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.domain.TrueData;
import com.mtx.metro.mapper.TrueMapper;
import com.mtx.metro.service.TrueService;

import java.util.Date;
import java.util.List;

public class TrueServiceImpl extends ServiceImpl<TrueMapper, TrueData> implements TrueService {

    private TrueMapper trueMapper;

    @Override
    public List<TrueData> selectAll() {
        return trueMapper.selectAll();
    }

    @Override
    public List<TrueData> selectAtATime(Date dateTime) {
        return trueMapper.selectAtATime(dateTime);
    }
}
