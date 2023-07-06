package com.mtx.metro.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mtx.metro.domain.PredictData;
import com.mtx.metro.mapper.PredictMapper;
import com.mtx.metro.service.PredictService;

import java.util.List;

public class PredictServiceImpl extends ServiceImpl<PredictMapper, PredictData> implements PredictService {

    private PredictMapper predictMapper;
    @Override
    public List<PredictData> selectAll() {
        return predictMapper.selectAll();
    }
}
