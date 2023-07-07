package com.mtx.metro.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mtx.metro.domain.PredictData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface PredictService extends IService<PredictData> {
    List<PredictData> selectAll();
}
