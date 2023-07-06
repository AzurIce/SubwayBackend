package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.domain.PredictData;
import com.mtx.metro.domain.TrueData;

import java.util.Date;
import java.util.List;

public interface TrueService extends IService<TrueData> {
    List<TrueData> selectAll();

    List<TrueData> selectAtATime(Date dateTime);

}
